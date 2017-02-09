package net.codebetweenthelines.telnet.telnetaction.delegator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import net.codebetweenthelines.logger.BasicLogger;
import net.codebetweenthelines.logger.Logger;
import net.codebetweenthelines.telnet.telnetaction.EchoTelnetAction;
import net.codebetweenthelines.telnet.telnetaction.TelnetAction;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ActionDelegator implements Runnable {
    private final Logger logger = new BasicLogger();
    private final Socket socket;
    private final Map<String, TelnetAction> telnetActionMap;
    private final String serverWelcome;

    private final TelnetAction echoAction = new EchoTelnetAction();

    public ActionDelegator(@Nonnull ActionDelegatorOptions actionDelegatorOptions) {
        this.socket = Objects.requireNonNull(actionDelegatorOptions.getSocket());
        this.telnetActionMap = Objects.requireNonNull(actionDelegatorOptions.getTelnetActionMap());
        this.serverWelcome = Objects.requireNonNull(actionDelegatorOptions.getServerWelcome());
    }

    @Override
    public void run() {
        try (final BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                final PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            writer.println(serverWelcome);

            boolean disconnect = false;
            Map<String, Object> stateMap = new HashMap<>();
            while (!disconnect) {
                String input = inputReader.readLine();
                if (input == null || input.trim().isEmpty()) {
                    continue;
                }

                if ("exit".equalsIgnoreCase(input)) {
                    disconnect = true;
                    continue;
                }

                Optional<String> actionResponse;
                if (telnetActionMap.isEmpty()) {
                    actionResponse = echoAction.execute(input, stateMap, socket);
                } else {
                    TelnetAction telnetAction = telnetActionMap.get(input.split(" ")[0]);
                    if (telnetAction != null) {
                        actionResponse = telnetAction.execute(input, stateMap, socket);
                    } else {
                        actionResponse = Optional.of("Unknown command.");
                    }
                }

                actionResponse.ifPresent(writer::println);
            }

        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }
}

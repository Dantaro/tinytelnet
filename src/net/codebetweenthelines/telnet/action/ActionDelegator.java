package net.codebetweenthelines.telnet.action;

import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import net.codebetweenthelines.logger.BasicLogger;
import net.codebetweenthelines.logger.Logger;
import net.codebetweenthelines.telnet.action.telnetaction.TelnetAction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ActionDelegator implements Runnable {
    private final Logger logger = new BasicLogger();
    private final Socket socket;
    private final Map<String, TelnetAction> telnetActionMap;
    private final String serverWelcome;

    public ActionDelegator(@NotNull ActionDelegatorOptions actionDelegatorOptions) {
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

                TelnetAction telnetAction = telnetActionMap.get(input.split(" ")[0]);
                if (telnetAction != null) {
                    Optional<String> actionResponse = telnetAction.execute(input, stateMap, socket);
                    actionResponse.ifPresent(writer::println);
                } else {
                    writer.println("Unknown command.");
                }
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

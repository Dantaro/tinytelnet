package net.codebetweenthelines.telnet.server;

import com.sun.istack.internal.NotNull;
import net.codebetweenthelines.logger.BasicLogger;
import net.codebetweenthelines.logger.Logger;
import net.codebetweenthelines.telnet.action.ActionDelegator;
import net.codebetweenthelines.telnet.action.ActionDelegatorOptions;
import net.codebetweenthelines.telnet.action.telnetaction.TelnetAction;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class TinyTelnetServer {
    private Logger logger = new BasicLogger();

    private Integer portNumber;

    private Integer maxThreads = 5; //Set to default
    private ExecutorService executorService;

    private Map<String, TelnetAction> telnetActionMap;
    private String serverWelcome;

    private TinyTelnetServer() {}

    TinyTelnetServer(@NotNull TinyTelnetServerFactory.TinyTelnetServerOptions tinyTelnetServerOptions) throws IOException {
        this.portNumber = Objects.requireNonNull(tinyTelnetServerOptions.getPortNumber());
        this.maxThreads = Objects.requireNonNull(tinyTelnetServerOptions.getMaxThreads());
        this.telnetActionMap = Objects.requireNonNull(tinyTelnetServerOptions.getTelnetActionMap());
        this.serverWelcome = Objects.requireNonNull(tinyTelnetServerOptions.getServerWelcome());
    }

    public void start() {
        executorService = Executors.newFixedThreadPool(maxThreads);
        try {
            ServerSocket serverSocket = new ServerSocket(this.portNumber);

            while (true) {
                ActionDelegatorOptions options = new ActionDelegatorOptions(telnetActionMap, serverSocket.accept(), serverWelcome);
                executorService.submit(new ActionDelegator(options));
            }

        } catch (IOException e) {
            logger.error(e);
        }
    }
}

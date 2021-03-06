package net.codebetweenthelines.telnet.server;

import net.codebetweenthelines.logger.BasicLogger;
import net.codebetweenthelines.logger.Logger;
import net.codebetweenthelines.telnet.telnetaction.TelnetAction;
import net.codebetweenthelines.telnet.telnetaction.delegator.ActionDelegator;
import net.codebetweenthelines.telnet.telnetaction.delegator.ActionDelegatorOptions;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class TinyTelnetServer {
    private Logger logger = new BasicLogger();

    private Integer portNumber;
    private Integer maxThreads;
    private Map<String, TelnetAction> telnetActionMap;
    private String serverWelcome;

    private boolean serverRunning = false;

    public static TinyTelnetServerBuilder getBuilder() {
        return TinyTelnetServerBuilder.getInstance();
    }

    private TinyTelnetServer() {}

    TinyTelnetServer(@Nonnull TinyTelnetServerBuilder.TinyTelnetServerOptions tinyTelnetServerOptions) throws IOException {
        this.portNumber = Objects.requireNonNull(tinyTelnetServerOptions.getPortNumber());
        this.maxThreads = Objects.requireNonNull(tinyTelnetServerOptions.getMaxThreads());
        this.telnetActionMap = Objects.requireNonNull(tinyTelnetServerOptions.getTelnetActionMap());
        this.serverWelcome = Objects.requireNonNull(tinyTelnetServerOptions.getServerWelcome());
    }

    public void start() {
        serverRunning = true;
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
        try {
            ServerSocket serverSocket = new ServerSocket(this.portNumber);

            while (serverRunning) {
                ActionDelegatorOptions options = new ActionDelegatorOptions(telnetActionMap, serverSocket.accept(), serverWelcome);
                executorService.submit(new ActionDelegator(options));
            }

        } catch (IOException e) {
            logger.error(e);
        }
    }

    public void stop() {
        serverRunning = false;
    }
}

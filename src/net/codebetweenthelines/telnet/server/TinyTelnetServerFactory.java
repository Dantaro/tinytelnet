package net.codebetweenthelines.telnet.server;

import com.sun.istack.internal.NotNull;

import java.io.IOException;
import net.codebetweenthelines.logger.BasicLogger;
import net.codebetweenthelines.logger.Logger;
import net.codebetweenthelines.telnet.telnetaction.TelnetAction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TinyTelnetServerFactory {

    private static Logger logger = new BasicLogger();

    private TinyTelnetServerOptions tinyTelnetServerOptions = new TinyTelnetServerOptions();

    private TinyTelnetServerFactory() {}

    public static TinyTelnetServerFactory getInstance() {
        return new TinyTelnetServerFactory();
    }

    public TinyTelnetServer build() throws IOException {
        return new TinyTelnetServer(tinyTelnetServerOptions);
    }

    public TinyTelnetServerFactory setMaxThreads(@NotNull Integer maxThreads) {
        tinyTelnetServerOptions.setMaxThreads(Objects.requireNonNull(maxThreads));
        return this;
    }

    public TinyTelnetServerFactory setPort(@NotNull Integer portNumber) {
        tinyTelnetServerOptions.setPortNumber(Objects.requireNonNull(portNumber));
        return this;
    }

    public TinyTelnetServerFactory setTelnetActionMap(@NotNull Map<String, TelnetAction> telnetActionMap) {
        tinyTelnetServerOptions.setTelnetActionMap(Objects.requireNonNull(telnetActionMap));
        return this;
    }

    final class TinyTelnetServerOptions {
        private Integer portNumber = 23;
        private Integer maxThreads = 5;
        private Map<String, TelnetAction> telnetActionMap = new HashMap<>();
        private String serverWelcome = "Connected to Server";

        private TinyTelnetServerOptions() {}

        Integer getPortNumber() {
            return portNumber;
        }

        private void setPortNumber(@NotNull Integer portNumber) {
            this.portNumber = Objects.requireNonNull(portNumber);
        }

        Integer getMaxThreads() {
            return maxThreads;
        }

        private void setMaxThreads(@NotNull Integer maxThreads) {
            this.maxThreads = Objects.requireNonNull(maxThreads);
        }

        Map<String, TelnetAction> getTelnetActionMap() {
            return telnetActionMap;
        }

        private void setTelnetActionMap(@NotNull Map<String, TelnetAction> telnetActionMap) {
            this.telnetActionMap = Objects.requireNonNull(telnetActionMap);
        }

        String getServerWelcome() {
            return serverWelcome;
        }

        private void setServerWelcome(@NotNull String serverWelcome) {
            this.serverWelcome = Objects.requireNonNull(serverWelcome);
        }
    }

}



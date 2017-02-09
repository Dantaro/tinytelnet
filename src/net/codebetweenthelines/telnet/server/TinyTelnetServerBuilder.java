package net.codebetweenthelines.telnet.server;

import java.io.IOException;

import net.codebetweenthelines.telnet.server.constant.TinyTelnetDefaultSettingConstants;
import net.codebetweenthelines.telnet.telnetaction.TelnetAction;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TinyTelnetServerBuilder {

    private TinyTelnetServerOptions tinyTelnetServerOptions = new TinyTelnetServerOptions();

    private TinyTelnetServerBuilder() {}

    public static TinyTelnetServerBuilder getInstance() {
        return new TinyTelnetServerBuilder();
    }

    public TinyTelnetServer build() throws IOException {
        return new TinyTelnetServer(tinyTelnetServerOptions);
    }

    public TinyTelnetServerBuilder setMaxThreads(@Nonnull Integer maxThreads) {
        tinyTelnetServerOptions.setMaxThreads(Objects.requireNonNull(maxThreads));
        return this;
    }

    public TinyTelnetServerBuilder setPort(@Nonnull Integer portNumber) {
        tinyTelnetServerOptions.setPortNumber(Objects.requireNonNull(portNumber));
        return this;
    }

    public TinyTelnetServerBuilder setTelnetActionMap(@Nonnull Map<String, TelnetAction> telnetActionMap) {
        tinyTelnetServerOptions.setTelnetActionMap(Objects.requireNonNull(telnetActionMap));
        return this;
    }

    public TinyTelnetServerBuilder setServerWelcome(@Nonnull String serverWelcome) {
        tinyTelnetServerOptions.setServerWelcome(Objects.requireNonNull(serverWelcome));
        return this;
    }

    final class TinyTelnetServerOptions {
        private Integer portNumber = TinyTelnetDefaultSettingConstants.DEFAULT_PORT;
        private Integer maxThreads = TinyTelnetDefaultSettingConstants.DEFAULT_MAX_THREADS;
        private Map<String, TelnetAction> telnetActionMap = new HashMap<>();
        private String serverWelcome = TinyTelnetDefaultSettingConstants.DEFAULT_SERVER_WELCOME;

        private TinyTelnetServerOptions() {}

        Integer getPortNumber() {
            return portNumber;
        }

        private void setPortNumber(@Nonnull Integer portNumber) {
            this.portNumber = Objects.requireNonNull(portNumber);
        }

        Integer getMaxThreads() {
            return maxThreads;
        }

        private void setMaxThreads(@Nonnull Integer maxThreads) {
            this.maxThreads = Objects.requireNonNull(maxThreads);
        }

        Map<String, TelnetAction> getTelnetActionMap() {
            return telnetActionMap;
        }

        private void setTelnetActionMap(@Nonnull Map<String, TelnetAction> telnetActionMap) {
            this.telnetActionMap = Objects.requireNonNull(telnetActionMap);
        }

        String getServerWelcome() {
            return serverWelcome;
        }

        private void setServerWelcome(@Nonnull String serverWelcome) {
            this.serverWelcome = Objects.requireNonNull(serverWelcome);
        }
    }

}



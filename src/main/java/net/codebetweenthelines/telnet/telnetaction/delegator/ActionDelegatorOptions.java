package net.codebetweenthelines.telnet.telnetaction.delegator;


import net.codebetweenthelines.telnet.telnetaction.TelnetAction;

import javax.annotation.Nonnull;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;

public class ActionDelegatorOptions {
    private final Map<String, TelnetAction> telnetActionMap;
    private final Socket socket;
    private final String serverWelcome;

    public ActionDelegatorOptions(@Nonnull Map<String, TelnetAction> telnetActionMap,
                                  @Nonnull Socket socket,
                                  @Nonnull String serverWelcome) {
        this.telnetActionMap = Objects.requireNonNull(telnetActionMap);
        this.socket = Objects.requireNonNull(socket);
        this.serverWelcome = Objects.requireNonNull(serverWelcome);
    }

    Map<String, TelnetAction> getTelnetActionMap() {
        return telnetActionMap;
    }

    Socket getSocket() {
        return socket;
    }

    String getServerWelcome() {
        return serverWelcome;
    }
}

package net.codebetweenthelines.telnet.action;

import com.sun.istack.internal.NotNull;
import net.codebetweenthelines.telnet.action.telnetaction.TelnetAction;

import java.net.Socket;
import java.util.Map;
import java.util.Objects;

public class ActionHandlerOptions {
    private final Map<String, TelnetAction> telnetActionMap;
    private final Socket socket;
    private final String serverWelcome;

    public ActionHandlerOptions(@NotNull Map<String, TelnetAction> telnetActionMap,
                                @NotNull Socket socket,
                                @NotNull String serverWelcome) {
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

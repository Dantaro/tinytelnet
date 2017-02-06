package net.codebetweenthelines.telnet.telnetaction;

import com.sun.istack.internal.NotNull;

import java.net.Socket;
import java.util.Map;
import java.util.Optional;

public interface TelnetAction {
    String getCommand();
    Optional<String> execute(@NotNull String userInput, @NotNull final Map<String, Object> stateMap, @NotNull Socket userSocket);
}

package net.codebetweenthelines.telnet.telnetaction;

import com.sun.istack.internal.NotNull;

import java.net.Socket;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EchoTelnetAction implements TelnetAction {

    @Override
    public String getCommand() {
        return "echo";
    }

    @Override
    public Optional<String> execute(@NotNull String userInput, @NotNull final Map<String, Object> stateMap, @NotNull Socket userSocket) {
        return Optional.ofNullable(Objects.requireNonNull(userInput));
    }
}

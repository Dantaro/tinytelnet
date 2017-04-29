package net.codebetweenthelines.telnet.telnetaction;

import javax.annotation.Nonnull;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EchoTelnetAction implements TelnetAction {

    @Override
    public @Nonnull String getCommand() {
        return "echo";
    }

    @Override
    public Optional<String> execute(@Nonnull String userInput, @Nonnull final Map<String, Object> stateMap, @Nonnull Socket userSocket) {
        return Optional.ofNullable(Objects.requireNonNull(userInput));
    }
}

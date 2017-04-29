package net.codebetweenthelines.telnet.telnetaction;

import javax.annotation.Nonnull;
import java.net.Socket;
import java.util.Map;
import java.util.Optional;

public interface TelnetAction {
    @Nonnull String getCommand();
    Optional<String> execute(@Nonnull String userInput, @Nonnull final Map<String, Object> stateMap, @Nonnull Socket userSocket);
}

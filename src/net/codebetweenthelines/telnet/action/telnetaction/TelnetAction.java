package net.codebetweenthelines.telnet.action.telnetaction;

import java.net.Socket;
import java.util.Map;
import java.util.Optional;

public interface TelnetAction {
    String getCommand();
    Optional<String> execute(String userInput, final Map<String, Object> stateMap, Socket userSocket);
}

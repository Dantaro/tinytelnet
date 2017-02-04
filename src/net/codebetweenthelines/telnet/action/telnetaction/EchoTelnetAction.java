package net.codebetweenthelines.telnet.action.telnetaction;

import java.net.Socket;
import java.util.Map;
import java.util.Optional;

public class EchoTelnetAction implements TelnetAction {
    @Override
    public String getCommand() {
        return "echo";
    }

    @Override
    public Optional<String> execute(String userInput, final Map<String, Object> stateMap, Socket userSocket) {
        //Strip out echo command
        userInput = userInput.replaceFirst("(echo )", "");
        return Optional.ofNullable(userInput);
    }
}

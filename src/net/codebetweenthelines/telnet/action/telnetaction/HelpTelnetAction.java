package net.codebetweenthelines.telnet.action.telnetaction;

import java.net.Socket;
import java.util.Map;
import java.util.Optional;

public class HelpTelnetAction implements TelnetAction {
    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public Optional<String> execute(String userInput, Map<String, Object> stateMap, Socket userSocket) {
        return Optional.of("Help commands: \n" +
                "help - displays help commands\n" +
                "echo - echos your statement\n" +
                "exit - close connection to server");
    }
}

package net.codebetweenthelines;

import net.codebetweenthelines.telnet.action.telnetaction.EchoTelnetAction;
import net.codebetweenthelines.telnet.action.telnetaction.HelpTelnetAction;
import net.codebetweenthelines.telnet.action.telnetaction.TelnetAction;
import net.codebetweenthelines.telnet.server.TinyTelnetServer;
import net.codebetweenthelines.telnet.server.TinyTelnetServerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String... args) throws IOException {
        Map<String, TelnetAction> telnetActionMap = new HashMap<>();
        buildTelnetActionMap(telnetActionMap);
        TinyTelnetServer tinyTelnetServer =
                TinyTelnetServerFactory
                        .getInstance()
                        .setTelnetActionMap(telnetActionMap)
                        .build();

        if (tinyTelnetServer != null) {
            tinyTelnetServer.start();
        }
    }

    private static void buildTelnetActionMap(Map<String, TelnetAction> telnetActionMap) {
        TelnetAction echoAction = new EchoTelnetAction();
        telnetActionMap.put(echoAction.getCommand(), echoAction);
        TelnetAction helpAction = new HelpTelnetAction();
        telnetActionMap.put(helpAction.getCommand(), helpAction);
    }
}

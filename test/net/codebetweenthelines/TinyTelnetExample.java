package net.codebetweenthelines;

import net.codebetweenthelines.telnet.telnetaction.EchoTelnetAction;
import net.codebetweenthelines.telnet.telnetaction.TelnetAction;
import net.codebetweenthelines.telnet.server.TinyTelnetServer;
import net.codebetweenthelines.telnet.server.TinyTelnetServerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TinyTelnetExample {
    public static void main(String... args) throws IOException {
        Map<String, TelnetAction> telnetActionMap = new HashMap<>();
        TinyTelnetServer tinyTelnetServer =
                TinyTelnetServerFactory
                        .getInstance()
                        .setTelnetActionMap(telnetActionMap)
                        .build();

        if (tinyTelnetServer != null) {
            tinyTelnetServer.start();
        }
    }
}

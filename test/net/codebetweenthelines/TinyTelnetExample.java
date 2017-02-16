package net.codebetweenthelines;

import net.codebetweenthelines.telnet.server.TinyTelnetServer;
import net.codebetweenthelines.telnet.server.TinyTelnetServerBuilder;

import java.io.IOException;

public class TinyTelnetExample {
    public static void main(String... args) throws IOException {
        TinyTelnetServer tinyTelnetServer =
                TinyTelnetServer
                        .getBuilder()
                        .build();

        tinyTelnetServer.start();
    }
}

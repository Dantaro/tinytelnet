package net.codebetweenthelines.telnetaction.delegator;

import net.codebetweenthelines.telnet.telnetaction.TelnetAction;
import net.codebetweenthelines.telnet.telnetaction.delegator.ActionDelegator;
import net.codebetweenthelines.telnet.telnetaction.delegator.ActionDelegatorOptions;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ActionDelegatorTest {

    @Test
    public void test_constructor() {
        Map<String, TelnetAction> telnetActionMap = new HashMap<>();
        Socket mocket = Mockito.mock(Socket.class);
        String serverWelcome = "Test";
        ActionDelegatorOptions actionDelegatorOptions = new ActionDelegatorOptions(telnetActionMap, mocket, serverWelcome);

        runExpectedPass(() -> new ActionDelegator(actionDelegatorOptions), "test_constructor");
        runExpectedFail(() -> new ActionDelegator(null), "test_constructor: null options object");
    }

    @Test
    public void test_ActionDelegatorOptions() {
        Socket mocket = Mockito.mock(Socket.class);

        runExpectedFail(() -> new ActionDelegatorOptions(null, mocket, ""), "test_ActionDelegatorOptions: null map");
        runExpectedFail(() -> new ActionDelegatorOptions(new HashMap<>(), null, ""), "test_ActionDelegatorOptions: null socket");
        runExpectedFail(() -> new ActionDelegatorOptions(new HashMap<>(), mocket, null), "test_ActionDelegatorOptions: null server welcome");
    }

    /*public void test_run() throws IOException {
        Socket mocket = Mockito.mock(Socket.class);

        Map<String, TelnetAction> telnetActionMap = new HashMap<>();
        String serverWelcome = "Test";
        ActionDelegatorOptions actionDelegatorOptions = new ActionDelegatorOptions(telnetActionMap, mocket, serverWelcome);
    }*/

    private void runExpectedPass(Runnable runnable, String message) {
        try {
            runnable.run();
            Assert.assertTrue(Boolean.TRUE);
        } catch (Exception e) {
            Assert.fail(message);
        }
    }

    private void runExpectedFail(Runnable runnable, String message) {
        try {
            runnable.run();
            Assert.fail(message);
        } catch (Exception e) {
            Assert.assertTrue(Boolean.TRUE);
        }
    }
}
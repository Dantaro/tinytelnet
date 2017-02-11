package net.codebetweenthelines.telnetaction;

import net.codebetweenthelines.telnet.telnetaction.EchoTelnetAction;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class EchoTelnetActionTest {

    @Test
    public void test_getCommand() {
        EchoTelnetAction echoTelnetAction = new EchoTelnetAction();
        Assert.assertEquals("echo", echoTelnetAction.getCommand());
    }

    @Test
    public void test_execute() {
        Map<String, Object> stateMap = new HashMap<>();
        Socket mocket = Mockito.mock(Socket.class);
        EchoTelnetAction echoTelnetAction = new EchoTelnetAction();
        Assert.assertEquals("test", echoTelnetAction.execute("test", stateMap, mocket).orElse("fail"));
        Assert.assertEquals("", echoTelnetAction.execute("", stateMap, mocket).orElse("fail"));

        try {
            echoTelnetAction.execute(null, stateMap, mocket);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

}

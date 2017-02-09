package net.codebetweenthelines.telnetaction;

import net.codebetweenthelines.telnet.telnetaction.EchoTelnetAction;
import org.junit.Assert;
import org.junit.Test;


public class EchoTelnetActionTest {

    @Test
    public void test_getCommand() {
        EchoTelnetAction echoTelnetAction = new EchoTelnetAction();
        Assert.assertEquals("echo", echoTelnetAction.getCommand());
    }

    @Test
    public void test_execute() {
        EchoTelnetAction echoTelnetAction = new EchoTelnetAction();
        Assert.assertEquals("test", echoTelnetAction.execute("test", null, null).orElse("fail"));
        Assert.assertEquals("", echoTelnetAction.execute("", null, null).orElse("fail"));

        boolean failure = false;
        try {
            echoTelnetAction.execute(null, null, null);
        } catch (NullPointerException e) {
            failure = true;
        }
        Assert.assertTrue(failure);
    }

}

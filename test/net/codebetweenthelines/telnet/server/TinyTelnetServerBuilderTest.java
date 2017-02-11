package net.codebetweenthelines.telnet.server;

import net.codebetweenthelines.telnet.server.constant.TinyTelnetDefaultSettingConstants;
import net.codebetweenthelines.telnet.telnetaction.EchoTelnetAction;
import net.codebetweenthelines.telnet.telnetaction.TelnetAction;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TinyTelnetServerBuilderTest {

    private final String portNumberFieldName = "portNumber";
    private final String maxThreadsFieldName = "maxThreads";
    private final String telnetActionMapFieldName = "telnetActionMap";
    private final String serverWelcomeFieldName = "serverWelcome";

    @Test
    public void test_getInstance() {
        TinyTelnetServerBuilder tinyTelnetServerBuilder = TinyTelnetServerBuilder.getInstance();
        Assert.assertNotNull(tinyTelnetServerBuilder);
    }

    @Test
    public void test_build() throws IOException, IllegalAccessException {
        TinyTelnetServer tinyTelnetServer = TinyTelnetServerBuilder.getInstance().build();
        Assert.assertNotNull(tinyTelnetServer);

        for (Field field : tinyTelnetServer.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            switch (field.getName()) {
                case portNumberFieldName:
                    Assert.assertTrue(field.get(tinyTelnetServer).equals(TinyTelnetDefaultSettingConstants.DEFAULT_PORT));
                    break;
                case maxThreadsFieldName:
                    Assert.assertTrue(field.get(tinyTelnetServer).equals(TinyTelnetDefaultSettingConstants.DEFAULT_MAX_THREADS));
                    break;
                case telnetActionMapFieldName:
                    Assert.assertTrue(((Map) field.get(tinyTelnetServer)).size() == 0);
                    break;
                case serverWelcomeFieldName:
                    Assert.assertTrue(field.get(tinyTelnetServer).equals(TinyTelnetDefaultSettingConstants.DEFAULT_SERVER_WELCOME));
                    break;
                case "serverRunning":
                    Assert.assertTrue(field.get(tinyTelnetServer).equals(Boolean.FALSE));
                    break;
                default:
            }
        }
    }

    @Test
    public void test_setPort() throws IOException, IllegalAccessException, NoSuchFieldException {
        int portNumber = 2001;
        TinyTelnetServer tinyTelnetServer = TinyTelnetServerBuilder.getInstance().setPort(portNumber).build();
        Field field = getField(tinyTelnetServer, portNumberFieldName);
        Assert.assertTrue(field.get(tinyTelnetServer).equals(portNumber));

        try {
            TinyTelnetServerBuilder.getInstance().setPort(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    //TODO Also unit test ActionDelegator. You'll need to mock a Socket so it returns a defined input and output stream.

    @Test
    public void test_setMaxThreads() throws IOException, IllegalAccessException, NoSuchFieldException {
        int maxThreads = 100;
        TinyTelnetServer tinyTelnetServer = TinyTelnetServerBuilder.getInstance().setMaxThreads(maxThreads).build();
        Field field = getField(tinyTelnetServer, maxThreadsFieldName);
        Assert.assertTrue(field.get(tinyTelnetServer).equals(maxThreads));

        try {
            TinyTelnetServerBuilder.getInstance().setMaxThreads(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void test_setServerWelcome() throws IOException, IllegalAccessException, NoSuchFieldException {
        String welcome = "Hello";
        TinyTelnetServer tinyTelnetServer = TinyTelnetServerBuilder.getInstance().setServerWelcome(welcome).build();
        Field field = getField(tinyTelnetServer, serverWelcomeFieldName);
        Assert.assertTrue(field.get(tinyTelnetServer).equals(welcome));
    }

    @Test
    public void test_setTelnetActionMap() throws IOException, IllegalAccessException, NoSuchFieldException {
        Map<String, TelnetAction> telnetActionHashMap = new HashMap<>();
        EchoTelnetAction echoTelnetAction = new EchoTelnetAction();
        telnetActionHashMap.put(echoTelnetAction.getCommand(), echoTelnetAction);
        TinyTelnetServer tinyTelnetServer = TinyTelnetServerBuilder.getInstance().setTelnetActionMap(telnetActionHashMap).build();
        Field field = getField(tinyTelnetServer, telnetActionMapFieldName);
        Assert.assertTrue(((Map) field.get(tinyTelnetServer)).size() == 1);

        try {
            TinyTelnetServerBuilder.getInstance().setTelnetActionMap(null);
            Assert.fail();
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    private Field getField(@Nonnull TinyTelnetServer tinyTelnetServer, @Nonnull String fieldName) throws NoSuchFieldException {
        Field field = tinyTelnetServer.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

}

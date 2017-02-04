package net.codebetweenthelines.logger;

public class BasicLogger implements Logger {
    @Override
    public void error(String message) {
        error(message, null);
    }

    @Override
    public void error(Throwable error) {
        error(null, error);
    }

    @Override
    public void error(String message, Throwable error) {
        if (message != null) {
            System.out.println(message);
        }
        if (error != null) {
            System.out.println(error.getMessage());
        }
    }
}

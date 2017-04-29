package net.codebetweenthelines.logger;

import javax.annotation.Nullable;

public class BasicLogger implements Logger {
    @Override
    public void error(@Nullable String message) {
        error(message, null);
    }

    @Override
    public void error(@Nullable Throwable error) {
        error(null, error);
    }

    @Override
    public void error(@Nullable String message, @Nullable Throwable error) {
        if (message != null) {
            System.out.println(message);
        }
        if (error != null) {
            System.out.println(error.getMessage());
        }
    }
}

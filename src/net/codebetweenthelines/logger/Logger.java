package net.codebetweenthelines.logger;

import javax.annotation.Nullable;

public interface Logger {
    void error(@Nullable String message);
    void error(@Nullable Throwable error);
    void error(@Nullable String message, @Nullable Throwable error);
}

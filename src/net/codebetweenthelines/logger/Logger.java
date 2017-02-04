package net.codebetweenthelines.logger;

public interface Logger {
    void error(String message);
    void error(Throwable error);
    void error(String message, Throwable error);
}

package by.victor.beta.logic.dbconection;

public class NoFreeConnectionException extends Exception {
    public NoFreeConnectionException() {
    }

    public NoFreeConnectionException(String message) {
        super(message);
    }

    public NoFreeConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFreeConnectionException(Throwable cause) {
        super(cause);
    }
}

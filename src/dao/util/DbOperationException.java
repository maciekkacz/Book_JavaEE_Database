package dao.util;

public class DbOperationException extends RuntimeException {
    public DbOperationException(Throwable cause) {
        super(cause);
    }
}
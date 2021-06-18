package by.kotik.dao.connectionpool;

public class ConnectionPollException extends Exception{
    private static final long serialVersionUID = 1L;

    public ConnectionPollException (Exception e) {
        super(e);
    }

    public ConnectionPollException(String message, Exception e) {
        super(message, e);
    }
}

package creadri.exceptions;

/**
 *
 * @author creadri
 */
public class MineException extends Exception {

    public MineException(Throwable cause) {
        super(cause);
    }

    public MineException(String message, Throwable cause) {
        super(message, cause);
    }

    public MineException(String message) {
        super(message);
    }

    public MineException() {
    }
}

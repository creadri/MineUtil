package creadri.exceptions;

/**
 *
 * @author creadri
 */
public class BddError extends MineException {

    public BddError(Throwable cause) {
        super(cause);
    }

    public BddError(String message, Throwable cause) {
        super(message, cause);
    }

    public BddError(String message) {
        super(message);
    }

    public BddError() {
    }
}

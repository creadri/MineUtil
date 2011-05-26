package creadri.exceptions;

/**
 *
 * @author creadri
 */
public class EmptySlot extends MineException {

    public EmptySlot(Throwable cause) {
        super(cause);
    }

    public EmptySlot(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptySlot(String message) {
        super(message);
    }

    public EmptySlot() {
    }

}

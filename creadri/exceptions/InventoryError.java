package creadri.exceptions;

/**
 *
 * @author creadri
 */
public class InventoryError extends MineException {

    public InventoryError(Throwable cause) {
        super(cause);
    }

    public InventoryError(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryError(String message) {
        super(message);
    }

    public InventoryError() {
    }
}

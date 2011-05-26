package creadri.exceptions;

/**
 *
 * @author creadri
 */
public class NoMoney extends MineException {

    public NoMoney(Throwable cause) {
        super(cause);
    }

    public NoMoney(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMoney(String message) {
        super(message);
    }

    public NoMoney() {
        super();
    }

}

package creadri.exceptions;

/**
 *
 * @author creadri
 */
public class InformationNotValid extends MineException {

    public InformationNotValid(Throwable cause) {
        super(cause);
    }

    public InformationNotValid(String message, Throwable cause) {
        super(message, cause);
    }

    public InformationNotValid(String message) {
        super(message);
    }

    public InformationNotValid() {
    }

}

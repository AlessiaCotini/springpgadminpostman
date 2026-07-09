package alessia.cotini.springpgadminpostman.exceptions;

public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}

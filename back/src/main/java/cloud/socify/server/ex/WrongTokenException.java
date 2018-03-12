package cloud.socify.server.ex;

public class WrongTokenException extends Exception {

    public WrongTokenException(String message) {
        super(message);
    }

}

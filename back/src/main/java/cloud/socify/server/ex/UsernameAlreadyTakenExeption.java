package cloud.socify.server.ex;

public class UsernameAlreadyTakenExeption extends Exception {

    public UsernameAlreadyTakenExeption(String message) {
        super(message);
    }

}

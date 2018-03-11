package cloud.socify.server.ex;

public class ChekingCredentialsFailedException extends Exception {
    private String username;

    public ChekingCredentialsFailedException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

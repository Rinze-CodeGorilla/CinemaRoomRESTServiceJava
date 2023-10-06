package cinema;

public class BadPasswordException extends Exception {
    BadPasswordException() {
        super("The password is wrong!");
    }
}

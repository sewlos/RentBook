package pl.edu.wszib.book.store.exceptions;

public class AuthValidationException extends RuntimeException {
    private final String info;

    public AuthValidationException(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}

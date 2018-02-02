package ua.lviv.courierdelivery.utils.exception;

public class EmptyFileException extends ApplicationException {
    public EmptyFileException() {
    }

    public EmptyFileException(String message) {
        super(message);
    }
}

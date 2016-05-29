package uk.co.mruoc.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "customer %s already exists";

    public CustomerAlreadyExistsException(String accountNumber) {
        super(String.format(MESSAGE, accountNumber));
    }

}

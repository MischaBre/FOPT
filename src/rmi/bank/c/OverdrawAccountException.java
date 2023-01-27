package rmi.bank.c;

public class OverdrawAccountException extends RuntimeException {
    public OverdrawAccountException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public OverdrawAccountException(String errorMessage) {
        super(errorMessage);
    }
}

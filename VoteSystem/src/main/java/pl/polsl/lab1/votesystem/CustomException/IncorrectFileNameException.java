package pl.polsl.lab1.votesystem.CustomException;

public class IncorrectFileNameException extends Exception {

    public IncorrectFileNameException() {}
    public IncorrectFileNameException(String errorMessage) {
        super(errorMessage);
    }
}

package pl.polsl.lab1.votesystem.CustomException;

/**
 * Exception for bad file name
 * @author Maciej-Plonka
 */
public class IncorrectFileNameException extends Exception {

    public IncorrectFileNameException() {}
    public IncorrectFileNameException(String errorMessage) {
        super(errorMessage);
    }
}

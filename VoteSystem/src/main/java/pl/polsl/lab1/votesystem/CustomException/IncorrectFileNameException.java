package pl.polsl.lab1.votesystem.CustomException;

/**
 * Exception for bad file name
 * @author Maciej-Plonka
 */
public class IncorrectFileNameException extends Exception {

    /**
     * constructor with no parameters
     */
    public IncorrectFileNameException() {}

    /**
     * constructor with parameters
     */
    public IncorrectFileNameException(String errorMessage) {
        super(errorMessage);
    }
}

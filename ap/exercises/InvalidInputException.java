package ap.exercises;

public class InvalidInputException extends RuntimeException
{
    public InvalidInputException(String message)
    {
        super(message);
    }
}
package ap.exercises.ex4;

public class LetterPrinter
{
    public static void main(String[] args)
    {
        try
        {
            Letter l=new Letter("Mery","John");
            l.addLine("I am sorry we must part.");
            l.addLine("I wish you all the best.");
            System.out.print(l.getText());
        }
        catch(InvalidInputException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

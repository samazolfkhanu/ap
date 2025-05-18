package ap.exercises.ex4;

public class Main_EX4_E3_10
{
    public static void main(String[] args)
    {
        CashRegister c=new CashRegister();
        try
        {
            c.addItem(3000);
            c.addItem(4000);
        }
        catch (InvalidInputException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println(c.printRecipient());
    }
}

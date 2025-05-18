package ap.exercises.midtermproject;

public class Main
{
    public static  void main(String[] args)
    {
        Library l=new Library("Central");
        Menu m=new Menu(l);
        try
        {
            m.run();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
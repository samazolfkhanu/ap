package ap.exercises.midtermproject;

public class Main
{
    public static  void main(String[] args)
    {
        Library l=new Library("Central");
        Menu m=new Menu(l);
        m.run();

    }
}
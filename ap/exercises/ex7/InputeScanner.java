package ap.exercises.ex7;

import java.util.Scanner;

public class InputeScanner
{
    private Scanner s=new Scanner(System.in);
    private int intOption;
    private String strOption;
    private Long LOption;
    public int getIntOption()
    {
        int m=s.nextInt();
        if(m>=0)
        {
            this.intOption=m;
            s.nextLine();
        }
        else
            throw new InvalidInputException("Invalid Input!");
        return intOption;
    }
    public String  getStringOption()
    {
        String m=s.nextLine();
        if(m!=null )
        {
            this.strOption=m;
        }
        else
            throw new InvalidInputException("Invalid Input!");
        return strOption;
    }
    public long getLongOption()
    {
        long m=s.nextInt();
        if(m>=0)
        {
            this.LOption=m;
            s.nextLine();
        }
        else
            throw new InvalidInputException("Invalid Input!");
        return LOption;
    }

}
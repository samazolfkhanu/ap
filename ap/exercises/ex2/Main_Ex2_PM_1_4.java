package ap.exercises.ex2;
import java.util.Scanner;

public class Main_Ex2_PM_1_4
{
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        String c;
        do {
            System.out.print("enter a character:");
            c=s.next().toLowerCase();
            try
            {
                if(!c.equals("w") && !c.equals("a") && !c.equals("s") && !c.equals("d") && !c.equals("q"))
                {
                    throw new IllegalArgumentException("WARNING\n");
                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.print(e.getMessage());
            }
            switch(c)
            {
                case "w":
                    System.out.print("UP\n");
                    break;

                case "a":
                    System.out.print("LEFT\n");
                    break;

                case "s":
                    System.out.print("DOWN\n");
                    break;

                case "d":
                    System.out.print("RIGHT\n");
                    break;

                case "q":
                    System.out.print("EXIT\n");

            }

        }while(!c.equals("q"));
    }
}


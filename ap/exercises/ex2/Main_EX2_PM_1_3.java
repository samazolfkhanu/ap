package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_1_3
{
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        System.out.print("enter the side of board:");
        int k=s.nextInt();
        s.nextLine();
        String[][] b=new String[k+2][k+2];
        for(int i=0;i<k+2;i++)
        {
            b[0][i]="*";
            b[i][0]="*";
            b[k+1][i]="*";
            b[i][k+1]="*";
        }
        for(int i=1;i<k+1;i++)
        {
            for(int j=1;j<k+1;j++)
            {
                b[i][j]=" ";
            }
        }
        boolean f=false;
        do
        {
            System.out.println("enter number of dots(food):");
            int c=s.nextInt();
            s.nextLine();
            try {
                if (c > (k * k))
                {
                    f=false;
                    throw new IllegalArgumentException("error!\nnumber of food is more than number of empty spaces!");
                }
                else {
                    Main_EX2_PM_1_3 m=new Main_EX2_PM_1_3();
                    m.placeDots(b, c);
                    for(int i=0;i<k+2;i++)
                    {
                        for(int j=0;j<k+2;j++)
                        {
                            System.out.print(b[i][j]);
                        }
                        System.out.print("\n");
                    }
                    f=true;
                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        }while(!f);

    }

    public void placeDots(String[][] b,int c)
    {
        Random r=new Random();
        int count=0;
        while(count<c)
        {
            int i=r.nextInt(b.length-2)+1;
            int j=r.nextInt(b[0].length-2)+1;
            if(b[i][j].equals(" "))
            {
                b[i][j]=".";
                count++;
            }
        }
    }


}

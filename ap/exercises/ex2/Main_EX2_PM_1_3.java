package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_1_3
{
    int k;
    String[][] b=new String[k+2][k+2];

    public void main(String[] args)
    {
        int c;
        Scanner s=new Scanner(System.in);
        System.out.print("enter the side of board:");
        k=s.nextInt();
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
            System.out.print("\n");
        }
        for(int i=0;i<k+2;i++)
        {
            for(int j=0;j<k+2;j++)
            {
                System.out.print(b[i][j]);
            }
            System.out.print("\n");
        }
        boolean x=false;
        do
        {
            System.out.print("enter number of foods(.):");
            c = s.nextInt();
            try
            {
                if(c>k*k)
                {
                    x=false;
                    IllegalArgumentException e=new IllegalArgumentException("the number of foods is large than number of empty plces!");
                    throw e;
                }
                x=true;
                break;
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }while(!x);
        placeDots(b,c);
        for(int i=0;i<k+2;i++)
        {
            for(int j=0;j<k+2;j++)
            {
                System.out.print(b[i][j]);
            }
            System.out.print("\n");
        }
    }

    public void placeDots(String[][] b,int c)
    {
        Random r=new Random();
        int count=0;
        while(count<c)
        {
            int i=r.nextInt(b.length);
            int j=r.nextInt(b[0].length);
            if(b[i][j].equals(" "))
            {
                b[i][j]=".";
                count++;
            }
        }
    }

}

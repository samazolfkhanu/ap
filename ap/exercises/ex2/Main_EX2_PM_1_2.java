package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_1_2
{
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        System.out.print("enter the side of board:");
        int k=s.nextInt();
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
        for(int i=0;i<k+2;i++)
        {
            for(int j=0;j<k+2;j++)
            {
                System.out.print(b[i][j]);
            }
            System.out.print("\n");
        }

    }
}


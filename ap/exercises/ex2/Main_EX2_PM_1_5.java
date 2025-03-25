package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class EX2_PM_1_5
{
    public void main(String[] args)
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
            System.out.print("\n");
        }
        int row=1,col=1;
        b[row][col]="X";

        while(true)
        {
            movePM(b,k,row,col);
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public void movePM(String[][] b, int k,int row,int col)
    {
        int z=row,y=col;
        Random random=new Random();
        int num=random.nextInt(4);
        switch(num)
        {
            case 0:
                if(row>1)
                {
                    System.out.println("UP");
                    row--;
                }
                break;

            case 1:
                if(col<k)
                {
                    System.out.println("RIGHT");
                    col++;
                }
                break;

            case 2:
                if(row<k)
                {
                    System.out.println("DOWN");
                    row++;
                }
                break;

            case 3:
                if(col>1)
                {
                    System.out.println("LEFT");
                    col--;
                }
                break;
        }
        if(b[row][col].equals(" "))
        {
            b[row][col]="X";
            b[z][y]=" ";
        }
        else {
            System.out.println("hitting the wall!");
            row = z;
            col = y;
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

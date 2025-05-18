package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_1_5
{
    String[][] b;
    int row=1,col=1;
    public static void main(String[] args)
    {
        Main_EX2_PM_1_5 t=new Main_EX2_PM_1_5();
        Scanner s=new Scanner(System.in);
        System.out.print("enter the side of board:");
        int k=s.nextInt();
        t.b=new String[k+2][k+2];
        for(int i=0;i<k+2;i++)
        {
            t.b[0][i]="*";
            t.b[i][0]="*";
            t.b[k+1][i]="*";
            t.b[i][k+1]="*";
        }
        for(int i=1;i<k+1;i++)
        {
            for(int j=1;j<k+1;j++)
            {
                t.b[i][j]=" ";
            }
        }
        t.b[t.row][t.col]="X";
        for(int i=0;i<k+2;i++)
        {
            for(int j=0;j<k+2;j++)
            {
                System.out.print(t.b[i][j]);
            }
            System.out.print("\n");
        }
        Move m=new Move();
        while(true)
        {
            m.move(k,t);
            for(int i=0;i<k+2;i++)
            {
                for(int j=0;j<k+2;j++)
                {
                    System.out.print(t.b[i][j]);
                }
                System.out.print("\n");
            }
            System.out.print("____________________________\n");
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

}
class Move
{
    int z,y;
    Random r=new Random();
    public void move(int k,Main_EX2_PM_1_5 t)
    {
        this.z=t.row;
        this.y=t.col;
        int num=r.nextInt(4);
        switch(num)
        {
            case 0:
                try {
                    if (t.row > 1) {
                        System.out.println("UP");
                        t.row--;
                    }
                    else
                    {
                        System.out.println("UP");
                        throw new Exception("can not move!");
                    }

                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                finally {
                    break;
                }

            case 1:
                try
                {
                    if (t.col<k) {
                        System.out.println("RIGTH");
                        t.col++;
                    }
                    else
                    {
                        System.out.println("RIGTH");
                        throw new Exception("can not move!");
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                finally
                {
                    break;
                }

            case 2:
                try
                {
                    if (t.row<k) {
                        System.out.println("DOWN");
                        t.row++;
                    }
                    else
                    {
                        System.out.println("DOWN");
                        throw new Exception("can not move!");
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                finally
                {
                    break;
                }

            case 3:
                try
                {
                    if (t.col>1) {
                        System.out.println("LEFT");
                        t.col--;
                    }
                    else
                    {
                        System.out.println("LEFT");
                        throw new Exception("can not move!");
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
                finally
                {
                    break;
                }
        }
        if(t.b[t.row][t.col].equals(" "))
        {
            t.b[t.row][t.col]="X";
            t.b[this.z][this.y]=" ";
        }
        else
        {
            t.row=this.z;
            t.col=this.y;
            System.out.println("Hiting the wall");
        }
    }

}


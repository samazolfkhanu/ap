package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_1
{
    String[][] b;
    int row=1,col=1;
    public void main(String[] args)
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
        MovePm m=new MovePm();
        m.move(k,t);

    }

}
class MovePm
{
    int z,y;
    Random r=new Random();
    public void move(int k,Main_EX2_PM_1_5 t)
    {
        Scanner s=new Scanner(System.in);
        System.out.print("choose to move:\n1.w=Up\n2.d=Right\n3.s=Down\n4.a=Left\n5.q=quit from game\n");
        String m;
        char o;
        do {
            m=s.next();
            m.toLowerCase();
            o=m.charAt(0);
            this.z=t.row;
            this.y=t.col;
            switch(o)
            {
                case 'w':
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

                case 'd':
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

                case 's':
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

                case 'a':
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

                case 'q':
                    System.out.println("exit the game...");
                    break;
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
            for(int i=0;i<k+2;i++)
            {
                for(int j=0;j<k+2;j++)
                {
                    System.out.print(t.b[i][j]);
                }
                System.out.print("\n");
            }
            System.out.print("____________________________\n");
        }while(o!='q');
    }
}


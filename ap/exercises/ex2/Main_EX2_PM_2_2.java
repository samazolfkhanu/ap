package ap.exercises.ex2;

import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_2
{
    //Length Of Game Board
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("enter the side of board:");
        int k = s.nextInt();
        s.nextLine();
        GameBoard g=new GameBoard();
        g.initializing(k);
        Food f=new Food();
        System.out.println("enter number of foods:");
        f.setc(s.nextInt());
        f.placeDots(g.b);
        MoveP p=new MoveP();
        p.move(g,k,f,g.b);
        System.out.println("score: "+p.getScorse()+"\ttime:"+Math.round(p.getTimeElapsed()/1000)+" s"+"\tExact time:"+(double)p.getTimeElapsed()/1000.0+" s");
    }
}
class GameBoard
{
    String[][]b; //Game Board
    int row=1,col=1;//Position of Pacman (X)
    public void initializing(int k)
    {
        Scanner s = new Scanner(System.in);
        this.b = new String[(k) + 2][k + 2];
        for (int i = 0; i < k + 2; i++) //initializing game board
        {
            for (int j = 0; j <k + 2; j++) {
                if (i == 0 || j == 0 || i ==k + 1 || j == k + 1)
                    this.b[i][j] = "*";
                else
                    this.b[i][j] = " ";
            }
        }
        this.b[this.row][this.col] = "X";
        System.out.print("____________________________\n");
    }
}
class Food
{
    private int c; //number of dots (food)
    public void setc(int c)
    {
        this.c=c;
    }

    public void placeDots(String[][] b)
    {
        int count = 0;
        Random r = new Random();
        while (count < c) {
            int i = r.nextInt(b.length - 2) + 1;
            int j = r.nextInt(b[0].length - 2) + 1;
            if (b[i][j].equals(" ")) {
                b[i][j] = ".";
                count++;
            }
        }
    }
    public int getC()
    {
        return c;
    }
}

class MoveP
{
    private int s; // s for scores
    private long timeElapsed;
    public void move(GameBoard O,int k,Food f,String[][] b)
    {
        int y,z; //z for saving the x_postion of X and y is for saving y_position of X also
        Scanner s=new Scanner(System.in);
        long start=System.currentTimeMillis();
        System.out.print("choose to move:\n1.w=Up\n2.d=Right\n3.s=Down\n4.a=Left\n5.q=quit from game\n");
        for (int i = 0; i < (k) + 2; i++) {
            for (int j = 0; j < (k )+ 2; j++) {
                System.out.print(b[i][j]);
            }
            System.out.print("\n");
        }
        String m;
        char o;
        do {
            m=s.next();
            m=m.toLowerCase();
            o=m.charAt(0);
            z=O.row;
            y=O.col;
           try
           {
               if(o!='w' && o!='a' && o!='s' && o!='d' && o!='q')
               {
                   throw new Exception("invalid input!");
               }
               else {
                   switch (o) {
                       case 'w':
                           try {
                               if (O.row > 1) {
                                   System.out.println("UP");
                                   O.row--;
                               } else {
                                   System.out.println("UP");
                                   throw new Exception("can not move!");
                               }

                           } catch (Exception e) {
                               System.out.println(e.getMessage());
                           } finally {
                               break;
                           }

                       case 'd':
                           try {
                               if (O.col < k) {
                                   System.out.println("RIGTH");
                                   O.col++;
                               } else {
                                   System.out.println("RIGTH");
                                   throw new Exception("can not move!");
                               }
                           } catch (Exception e) {
                               System.out.println(e.getMessage());
                           } finally {
                               break;
                           }

                       case 's':
                           try {
                               if (O.row < k) {
                                   System.out.println("DOWN");
                                   O.row++;
                               } else {
                                   System.out.println("DOWN");
                                   throw new Exception("can not move!");
                               }
                           } catch (Exception e) {
                               System.out.println(e.getMessage());
                           } finally {
                               break;
                           }

                       case 'a':
                           try {
                               if (O.col > 1) {
                                   System.out.println("LEFT");
                                   O.col--;
                               } else {
                                   System.out.println("LEFT");
                                   throw new Exception("can not move!");
                               }
                           } catch (Exception e) {
                               System.out.println(e.getMessage());
                           } finally {
                               break;
                           }

                       case 'q':
                           System.out.println("exit the game...");
                           break;
                   }
                   if (b[O.row][O.col].equals(" ")) {
                       b[O.row][O.col] = "X";
                       b[z][y] = " ";
                   } else if (b[O.row][O.col].equals(".")) {
                       b[O.row][O.col] = "X";
                       b[z][y] = " ";
                       f.setc((f.getC()) - 1);
                       score();
                   }
                   else if(b[O.row][O.col].equals("*"))
                   {
                       O.row = z;
                       O.col = y;
                       System.out.println("Hiting the wall");
                   }
                   for (int i = 0; i < k + 2; i++) {
                       for (int j = 0; j < k + 2; j++) {
                           System.out.print(b[i][j]);
                       }
                       System.out.print("\n");
                   }
                   System.out.print("____________________________\n");
               }
               long finish=System.currentTimeMillis();
               this.timeElapsed=finish-start;
           }
           catch(Exception er)
           {
               System.out.println(er.getMessage());
           }
        }while(o!='q' && f.getC()!=0 );
    }

    public long getTimeElapsed()
    {
        return timeElapsed;
    }

    public void score()
    {
        this.s++;
    }
    public int getScorse()
    {
        return s;
    }
}

package ap.exercises.ex2;

import java.io.File;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main_EX2_PM_2_3
{
    
    public static void main(String[] args)
    {
        FileP l=new FileP();
        Scanner s=new Scanner(System.in);
        int c;
        Dot f=new Dot();
        Board g=new Board();
        MovePM p=new MovePM();
        do
        {
            System.out.println("1.new game\n2.continue the last game\n3.exit\n");
            c=s.nextInt();
            s.nextLine();
            switch(c)
            {

                case 1:
                    g.initializing();
                    System.out.println("enter number of foods:");
                    f.setc(s.nextInt());
                    f.placeDots(g.b);
                    p.move(g,f);
                    System.out.println("score: "+p.getScore()+"\ttime:"+Math.round(p.getTimeElapsed()/1000)+" s"+"\tExact time:"+(double)p.getTimeElapsed()/1000.0+" s");
                    l.write(g,p);
                    break;

                case 2:
                    if(l.read(g,p))
                    {
                        p.move(g,f);
                        System.out.println("score: "+p.getScore()+"\ttime:"+Math.round(p.getTimeElapsed()/1000)+" s"+"\tExact time:"+(double)p.getTimeElapsed()/1000.0+" s");
                        l.write(g,p);
                    }

                    break;

                case 3:
                    System.out.println("exit the game...");
                    break;
            }
        }while(c!=3);
    }
}

class FileP
{
    String s="F:/JavaProject/ap/exercises/ex2/PM.txt";
    public void write(Board b, MovePM p)
    {
        if (s.length() == 0) {
            System.out.println("The file is empty!");
        } else {
            try
            {
                PrintWriter pw=new PrintWriter(new File(s));
                pw.println(b.k);
                pw.println(p.getScore());
                for(char[] c : b.b)
                {
                    pw.write(c);
                    pw.write("\n");
                }
                pw.flush();
                pw.close();
                System.out.println("Information stored successfully!");
            } catch (Exception e) {
                System.out.println("Error while writing file: " + e.getMessage());
            }
        }
    }

    public boolean read(Board b, MovePM p) {
        try {
            Scanner scan = new Scanner(new File(s));
            b.k = scan.nextInt();
            scan.nextLine();
            p.sc = scan.nextInt();
            System.out.println("Loaded board size: " + b.k);
            System.out.println("Loaded score: " + p.sc);
            scan.nextLine();
            b.b = new char[b.k + 2][b.k + 2];

            int i = 0;
            while (scan.hasNextLine() && i < b.k + 2) {
                String line = scan.nextLine();
                for (int j = 0; j < (b.k)+2; j++) {
                    b.b[i][j] = line.charAt(j);
                    if (b.b[i][j] == 'X')
                    {
                        b.row = i;
                        b.col = j;
                    }
                }
                i++;
            }
            scan.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error while reading file: " + e.getMessage());
            return false;
        }
    }
}

class Board
{
    char[][]b; //Game Board
    int row=1,col=1;
    int k;
    public void initializing()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("enter the side of board:");
        k = s.nextInt();
        s.nextLine();
        this.b = new char[(k) + 2][k + 2];
        for (int i = 0; i < k + 2; i++) //initializing game board
        {
            for (int j = 0; j <k + 2; j++) {
                if (i == 0 || j == 0 || i ==k + 1 || j == k + 1)
                    this.b[i][j] = '*';
                else
                    this.b[i][j] = ' ';
            }
        }
        this.b[this.row][this.col] = 'X';
        System.out.print("____________________________\n");
    }
}
class Dot
{
    private int c; //number of dots (food)
    public void setc(int c)
    {
        this.c=c;
    }

    public void placeDots(char[][] b)
    {

        int count = 0;
        Random r = new Random();
        while (count < c) {
            int i = r.nextInt(b.length - 2) + 1;
            int j = r.nextInt(b[0].length - 2) + 1;
            if (b[i][j]==' ') {
                b[i][j] = '.';
                count++;
            }
        }
    }
    public int getC()
    {
        return c;
    }
}

class MovePM
{
    protected int sc; // s for scores
    private long timeElapsed;
    public void move(Board O,Dot f)
    {
        Scanner s=new Scanner(System.in);
        long start=System.currentTimeMillis();
        System.out.print("choose to move:\n1.w=Up\n2.d=Right\n3.s=Down\n4.a=Left\n5.q=quit from game\n");
        for (int i = 0; i < (O.k) + 2; i++) {
            for (int j = 0; j < (O.k )+ 2; j++) {
                System.out.print(O.b[i][j]);
            }
            System.out.print("\n");
        }
        String m;
        char o=' ';
        do {
            System.out.print("Enter move: ");
            m=s.next();
            m=m.toLowerCase();
            o=m.charAt(0);
            System.out.println(o+"\n");
            int prevRow=O.row;
            int prevCol=O.col;
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
                                    throw new Exception("Hitting the wall");
                                }

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            } finally {
                                break;
                            }

                        case 'd':
                            try {
                                if (O.col < O.k) {
                                    System.out.println("RIGHT");
                                    O.col++;
                                } else {
                                    System.out.println("RIGHT");
                                    throw new Exception("Hitting the wall");
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            } finally {
                                break;
                            }

                        case 's':
                            try {
                                if (O.row < O.k) {
                                    System.out.println("DOWN");
                                    O.row++;
                                } else {
                                    System.out.println("DOWN");
                                    throw new Exception("Hitting the wall");
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
                                    throw new Exception("Hitting the wall");
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
                    if (O.b[O.row][O.col]==' ') {
                        O.b[O.row][O.col] = 'X';
                        O.b[prevRow][prevCol] = ' ';
                    } else if (O.b[O.row][O.col]=='.') {
                        O.b[O.row][O.col] ='X';
                        O.b[prevRow][prevCol] = ' ';
                        f.setc((f.getC())-1);
                        score();
                    }
                    for (int i = 0; i < O.k + 2; i++) {
                        for (int j = 0; j < O.k + 2; j++) {
                            System.out.print(O.b[i][j]);
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
        this.sc++;
    }
    public int getScore()
    {
        return sc;
    }
}




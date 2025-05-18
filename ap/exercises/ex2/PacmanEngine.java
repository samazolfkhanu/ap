package ap.exercises.ex2;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;

class PacmanEngine
{
    char[][]b;
    private int s;
    int row=1,col=1;
    private int k;
    private int c;
    long finish,start,timeElapsed;
    String sr="F:/MainProjects/ap/exercises/ex2/PM.txt";
    public PacmanEngine(int k,int c)
    {
        try
        {
            if(c>0 && k>0)
            {
                this.k=k;
                this.c=c;
                this.b = new char[(k) + 2][k + 2];
                for (int i = 0; i < k + 2; i++) //initializing game board
                {
                    for (int j = 0; j <k + 2; j++)
                    {
                        if (i == 0 || j == 0 || i ==k + 1 || j == k + 1)
                            this.b[i][j] = '*';
                        else
                            this.b[i][j] = ' ';
                    }
                }
                this.b[this.row][this.col] = 'X';
                int count = 0;
                Random r = new Random();
                while (count < c)
                {
                    int i = r.nextInt(b.length - 2) + 1;
                    int j = r.nextInt(b[0].length - 2) + 1;
                    if (b[i][j]==' ') {
                        b[i][j] = '.';
                        count++;
                    }
                }
                System.out.print("____________________________\n");
            }
            else
                throw new InvalidInputException("invalid input!");
        }
        catch(InvalidInputException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void printMatrix()
    {
        start=System.currentTimeMillis();
        for (int i = 0; i < k + 2; i++)
        {
            for (int j = 0; j <k + 2; j++)
            {
                System.out.print(b[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("____________________________\n");
    }

    public void score()
    {
        this.s++;
    }
    public void printScore()
    {
        System.out.println("Score :"+s);
    }
    public void move(int d)
    {
        int y, z; //z for saving the x_postion of X and y is for saving y_position of X
        z = row;
        y = col;
        try {
            if (d != 0 && d != 1 && d != 2 && d != 3)
            {
                throw new Exception("invalid input!");
            } else {
                switch (d)
                {
                    case 0:
                        try
                        {
                            if (row > 1)
                            {
                                System.out.println("UP");
                                row--;
                            } else
                            {
                                System.out.println("UP");
                                throw new Exception("Hitting the wall");
                            }

                        } catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        }
                        finally
                        {
                            break;
                        }

                    case 1:
                        try {
                            if (col < k)
                            {
                                System.out.println("RIGHT");
                                col++;
                            } else {
                                System.out.println("RIGHT");
                                throw new Exception("Hitting the wall");
                            }
                        } catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        } finally {
                            break;
                        }

                    case 2:
                        try {
                            if (row < k) {
                                System.out.println("DOWN");
                                row++;
                            } else {
                                System.out.println("DOWN");
                                throw new Exception("Hitting the wall");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        } finally {
                            break;
                        }

                    case 3:
                        try {
                            if (col > 1) {
                                System.out.println("LEFT");
                                col--;
                            } else {
                                System.out.println("LEFT");
                                throw new Exception("Hitting the wall");
                            }
                        } catch (Exception e)
                        {
                            System.out.println(e.getMessage());
                        } finally {
                            break;
                        }

                }
                if (b[row][col] == ' ') {
                    b[row][col] = 'X';
                    b[z][y] = ' ';
                } else if (b[row][col] == '.') {
                    b[row][col] = 'X';
                    b[z][y] = ' ';
                    c -= 1;
                    score();
                }

                long finish = System.currentTimeMillis();
                this.timeElapsed = finish - start;
            }
        }
        catch (Exception er)
        {
            System.out.println(er.getMessage());
        }
    }
    public void printRemainTime()
    {
        System.out.println("Time: "+(timeElapsed/1000));;
    }
    public void save()
    {
        if (sr.isEmpty()) {
            System.out.println("The file is empty!");
        } else {
            try
            {
                PrintWriter pw=new PrintWriter(sr);
                pw.println(k);
                pw.println(c);
                for(char[] c : b)
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
}



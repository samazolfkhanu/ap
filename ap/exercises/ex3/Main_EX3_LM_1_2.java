package ap.exercises.ex3;

import java.io.*;
import java.util.Scanner;

public class Main_EX3_LM_1_2
{
    public static void main(String[] args)  {
        Scanner s = new Scanner(System.in);
        int c, c1, c2;
        do {
            System.out.println("1.Book\n2.Student\n3.exit\n");
            c = s.nextInt();
            switch (c) {
                case 1:
                    do {
                        System.out.println("1.add\n2.exit\n");
                        c1 = s.nextInt();
                        s.nextLine();
                        switch (c1) {
                            case 1:
                                System.out.println("enter number of books: ");
                                int nb = s.nextInt();
                                s.nextLine();
                                Book[] b = new Book[nb];
                                for (int i = 0; i < nb; i++) {
                                    System.out.println("enter name of book,name of author,number of pages and published year:");
                                    String bN = s.nextLine();
                                    String aN = s.nextLine();
                                    int nP = s.nextInt();
                                    int pY = s.nextInt();
                                    s.nextLine();
                                    try
                                    {
                                        b[i] = new Book(bN, aN, nP, pY);
                                    }
                                    catch(InvalidInputException e)
                                    {
                                        System.out.println(e.getMessage());
                                    }
                                    try
                                    {
                                        File f=new File("F:/MainProjects/ap/exercises/ex3/Book.txt");
                                        ObjectOutputStream O;
                                        if(f.length()==0)
                                        {
                                            O=new ObjectOutputStream(new FileOutputStream("F:/MainProjects/ap/exercises/ex3/Book.txt"));
                                            O.writeObject(b[i]);
                                        }
                                        else
                                        {
                                            O=new AppendObj(new FileOutputStream("F:/MainProjects/ap/exercises/ex3/Book.txt",true));
                                            O.writeObject(b[i]);
                                        }
                                    }
                                    catch(Exception e)
                                    {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                break;

                            case 2:
                                System.out.println("exiting...");

                        }
                    } while (c1 != 2);
                    break;

                case 2:
                    do {
                        System.out.println("1.add\n2.exit\n");
                        c2 = s.nextInt();
                        s.nextLine();
                        switch (c2) {
                            case 1:
                                System.out.println("enter number of student: ");
                                int ns = s.nextInt();
                                s.nextLine();
                                Student[] st = new Student[ns];
                                for (int i = 0; i < ns; i++) {
                                    System.out.println("enter name ,family name,id and discipline:");
                                    String name = s.nextLine();
                                    String familyname = s.nextLine();
                                    long id = s.nextLong();
                                    s.nextLine();
                                    String dis = s.nextLine();
                                    try
                                    {
                                        st[i] = new Student(name, familyname, id, dis);
                                    }
                                    catch(InvalidInputException e)
                                    {
                                        System.out.println(e.getMessage());
                                    }
                                    try
                                    {
                                        File f=new File("F:/MainProjects/ap/exercises/ex3/Student.txt");
                                        ObjectOutputStream O;
                                        if(f.length()==0)
                                        {
                                            O=new ObjectOutputStream(new FileOutputStream("F:/MainProjects/ap/exercises/ex3/Student.txt"));
                                            O.writeObject(st[i]);
                                        }
                                        else
                                        {
                                            O=new AppendObj(new FileOutputStream("F:/MainProjects/ap/exercises/ex3/Student.txt",true));
                                            O.writeObject(st[i]);
                                        }
                                    }
                                    catch(Exception e)
                                    {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                break;

                            case 2:
                                System.out.println("exiting...");

                        }
                    } while (c2 != 2);
                    break;

                case 3:
                    System.out.println("exiting...");
                    break;
            }
        }while(c!=3);
    }
}


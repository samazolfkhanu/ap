package ap.exercises.ex3;

import java.io.*;
import java.util.Scanner;

public class Main_EX3_LM_1_2
{
    public static void main(String[] args) throws InvalidInputExceptionErr {
        Scanner s=new Scanner(System.in);
        BookLib[] b=new BookLib[4];
        for(int i=0;i<4;i++)
        {
            System.out.println("enter name of book,name of author,number of pages and published year:");
            String bN=s.nextLine();
            String aN=s.nextLine();
            int nP=s.nextInt();
            int pY=s.nextInt();
            s.nextLine();
            b[i]=new BookLib(bN,aN,nP,pY);
            writeToFileB(b[i]);

        }
        StudentC[] st=new StudentC[3];
        for(int i=0;i<3;i++)
        {
            System.out.println("enter name ,family name,id and discipline:");
            String name=s.nextLine();
            String familyname=s.nextLine();
            long id=s.nextLong();
            s.nextLine();
            String dis=s.nextLine();
            st[i]=new StudentC(name,familyname,id,dis);
            writeToFileS(st[i]);

        }
    }

    public static void writeToFileB(BookLib b)
    {
        try
        {
            File f=new File("F:/MainProjects/ap/exercises/ex3/Book.txt");
            ObjectOutputStream O;
            if(f.length()==0)
            {
                O=new ObjectOutputStream(new FileOutputStream("F:/MainProjects/ap/exercises/ex3/Book.txt"));
                O.writeObject(b);
            }
            else
            {
                O=new AppendObj(new FileOutputStream("F:/MainProjects/ap/exercises/ex3/Book.txt",true));
                O.writeObject(b);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void writeToFileS(StudentC s)
    {
        try
        {
            File f=new File("F:/MainProjects/ap/exercises/ex3/Student.txt");
            ObjectOutputStream O;
            if(f.length()==0)
            {
                O=new ObjectOutputStream(new FileOutputStream("F:/MainProjects/ap/exercises/ex3/Student.txt"));
                O.writeObject(s);
            }
            else
            {
                O=new AppendObj(new FileOutputStream("F:/MainProjects/ap/exercises/ex3/Student.txt",true));
                O.writeObject(s);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}

class BookLib implements Serializable
{
    private String bN;
    private String aN;
    private int nP;
    private int pY;
    public BookLib()
    {

    }
    public BookLib(String bN,String aN,int nP,int pY) throws InvalidInputExceptionErr {
        if(bN!=null && aN!=null && nP>0 && pY>0)
        {
            this.bN = bN;
            this.aN = aN;
            this.nP = nP;
            this.pY = pY;
        }
        else {
            throw new InvalidInputExceptionErr("Error: invalid input!");
        }
    }

    public String getBookName()
    {
        return bN;
    }
    public void setBookName(String bN)
    {
        this.bN=bN;
    }

    public String getAuthorName()
    {
        return aN;
    }
    public void setAuthorName(String aN)
    {
        this.aN=aN;
    }

    public int getNumPage()
    {
        return nP;
    }
    public void setNumPage(int nP)
    {
        this.nP=nP;
    }

    public int getPublishedYear()
    {
        return pY;
    }
    public void setPublishedYear(int pY)
    {
        this.pY=pY;
    }

    @Override
    public String toString()
    {
        return "book name: "+this.bN+"\tauthor name: "+aN+"\tnumber of pages: "+nP+"\tyear of published:"+pY+"\n";
    }
}

class StudentC implements Serializable
{
    private String n;
    private String fN;
    private long id;
    private String dis;

    public StudentC()
    {

    }

    public StudentC(String n,String fN,long id,String dis) throws InvalidInputExceptionErr {
        if(n!=null && fN!=null && id>0 && dis!=null)
        {
            this.n=n;
            this.fN=fN;
            this.id=id;
            this.dis=dis;
        }
        else
            throw new InvalidInputExceptionErr("Error: invalid input!");
    }

    public String getName()
    {
        return n;
    }
    public void setName(String n)
    {
        this.n=n;
    }

    public String getFamilyName()
    {
        return fN;
    }
    public void setFamilyName(String fN)
    {
        this.fN=fN;
    }

    public long getid()
    {
        return id;
    }
    public void setid(long id)
    {
        this.id=id;
    }

    public String getDiscpline()
    {
        return dis;
    }
    public void setDiscpline(String dis)
    {
        this.dis=dis;
    }

    @Override
    public String toString()
    {
        return "Student name: "+this.n+"\tfamily name: "+fN+"\tid: "+id+"\tdiscpline: "+dis+"\n";
    }
}

class AppendObj extends ObjectOutputStream
{
    public AppendObj(OutputStream out) throws Exception
    {
        super(out);
    }
    @Override
    public void writeStreamHeader() throws IOException
    {
        reset();
    }
}

class InvalidInputExceptionErr extends Exception
{
    public InvalidInputExceptionErr(String message)
    {
        super(message);
    }
}
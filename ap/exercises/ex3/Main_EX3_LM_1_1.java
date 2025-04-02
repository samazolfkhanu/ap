package ap.exercises.ex3;

public class Main_EX3_LM_1_1
{
    public static void main(String[] args)
    {
        try
        {
            Book[] b=new Book[2];
            b[0]=new Book("The Gifts Of Imperfection","Brene Brown",138,2010);
            b[1]=new Book("ABC","DEF",349,2006);

            Student[] s=new Student[2];
            s[0]=new Student("abc","fff",675432626,"hfgf");
            s[1]=new Student("hfgf","skjd",736863,"dss");
            for(Book B:b)
            {
                System.out.println(B.toString());
            }
            for(Student S:s)
            {
                System.out.println(S.toString());
            }

        }
        catch (InvalidInputException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

class Book
{
    private String bN;
    private String aN;
    private int nP;
    private int pY;
    public Book(String bN,String aN,int nP,int pY)throws InvalidInputException
    {
        if(bN!=null && aN!=null && nP>0 && pY>0)
        {
            this.bN = bN;
            this.aN = aN;
            this.nP = nP;
            this.pY = pY;
        }
        else {
            throw new InvalidInputException("Error: invalid input!");
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

class Student
{
    private String n;
    private String fN;
    private long id;
    private String dis;

    public Student(String n,String fN,long id,String dis)throws InvalidInputException
    {
        if(n!=null && fN!=null && id>0 && dis!=null)
        {
            this.n=n;
            this.fN=fN;
            this.id=id;
            this.dis=dis;
        }
        else
            throw new InvalidInputException("Error: invalid input!");
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

class InvalidInputException extends Exception
{
    public InvalidInputException(String message)
    {
        super(message);
    }
}

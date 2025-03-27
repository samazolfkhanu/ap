package ap.exercises.ex3;

public class Main_EX3_LM_1_1
{
    public void main(String[] args)
    {
        Book b1=new Book("The Gifts Of Imperfection","Brene Brown",138,2010);
        Book b2=new Book("ABC","DEF",349,2006);

        Student s1=new Student("abc","fff",675432626,"hfgf");
        Student s2=new Student("hfgf","skjd",736863,"dss");

    }
}

class Book
{
    private String bN;
    private String aN;
    private int nP;
    private int pY;
    public Book(String bN,String aN,int nP,int pY)
    {
        setInfo(bN,aN,nP,pY);
    }
    public void setInfo(String bN,String aN,int nP,int pY)
    {
        if(bN!=null && aN!=null && nP!=0 && pY!=0)
        {
            this.bN = bN;
            this.aN = aN;
            this.nP = nP;
            this.pY = pY;
        }
        else {
            ///////////;
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

    public Student(String n,String fN,long id,String dis)
    {
        setInfo(n,fN,id,dis);
    }
    public void setInfo(String n,String fN,long id,String dis)
    {
        if(n!=null && fN!=null && id!=0 && dis!=null)
        {
            this.n=n;
            this.fN=fN;
            this.id=id;
            this.dis=dis;
        }
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

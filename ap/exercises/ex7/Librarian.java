package ap.exercises.ex7;

import java.io.Serializable;
import java.util.Random;
import java.util.function.Function;

public class Librarian implements Serializable,HashId
{
    private String n;
    private String fN;
    private Long id;
    private int nOBB=0;
    private int nORB=0;


    public Librarian(){}

    public Librarian(String n,String fN,long id)throws InvalidInputException
    {
        if(n!=null && fN!=null && id>0)
        {
            this.n=n;
            this.fN=fN;
            this.id=id;
        }
        else
            throw new InvalidInputException("Invalid Input!");
    }

    public String toTabSeparatedString()
    {
        return String.join("\t",n,fN,String.valueOf(id),String.valueOf(nOBB),String.valueOf(nORB));
    }
    public static Librarian fromTabSeparated(String line) throws InvalidInputException
    {
        String[] p=line.split("\t");
        String n=p[0];
        String f=p[1];
        Long id=Long.parseLong(p[2]);
        int nobb=Integer.parseInt(p[3]);
        int norb=Integer.parseInt(p[4]);
        Librarian l=new Librarian(n,f,id);
        for(int i=0;i<nobb;i++)
        {
            l.increasenOBB();
        }
        for(int i=0;i<norb;i++)
        {
            l.increasenORB();
        }
        return l;
    }
    public void increasenOBB()
    {
        nOBB++;
    }
    public int getnOBB()
    {
        return nOBB;
    }

    public void increasenORB()
    {
        nORB++;
    }
    public int getnORB()
    {
        return nORB;
    }

    public void setName(String name)
    {
        if(name!=null)
            this.n=name;
        else
            throw new NullPointerException("Name is Null!");
    }
    public String getName()
    {
        return n;
    }

    public void setFamilyName(String fname)
    {
        if(fname!=null)
            this.fN=fname;
        else
            throw new NullPointerException("Family Name is Null!");
    }
    public String getFamilyName()
    {
        return fN;
    }

    public void setId(Long id)
    {
        if(id>0)
            this.id=id;
        else
            throw new InvalidInputException("value of Id can not be Negative!");
    }
    public Long getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return ">>Librarian Information:\n"+"\tName: "+n+"\n"+"\tFamily Name: "+fN+"\n"+"\tId: "+id+"\n"+"\tBorrowed Books: "+nOBB+"\n"+"\tReturned Book: "+nORB+"\n";
    }


}
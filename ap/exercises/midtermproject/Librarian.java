package ap.exercises.midtermproject;

import java.io.Serializable;
import java.util.Random;

public class Librarian implements Serializable,HashId
{
    private static final long sv=1L;
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
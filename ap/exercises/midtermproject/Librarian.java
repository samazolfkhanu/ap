package ap.exercises.midtermproject;

import java.io.Serializable;
import java.util.Random;

public class Librarian implements Serializable,HashId<Librarian>
{
    private static final long sv=1L;
    private String n;
    private String fN;
    private Long id;
    private int nOBB;
    private int nORB;

    public Librarian(){}

    public Librarian(String n,String fN,long id)throws InvalidInputException
    {
        if(n!=null && fN!=null && id>0)
        {
            this.n=n;
            this.fN=fN;
            this.id=id;
            this.nOBB=0;
            this.nORB=0;
        }
        else
            throw new InvalidInputException("Invalid input!");
    }

    public void setN(String n)throws InvalidInputException
    {
        if(n!=null) {
            this.n = n;
        }
        else
            throw new InvalidInputException("Invalid input!");
    }
    public String getN()
    {
        return n;
    }

    public void setFN(String fN)throws InvalidInputException
    {
        if(fN!=null)
            this.fN=fN;
        else
            throw new InvalidInputException("Invalid input!");
    }
    public String getFN()
    {
        return fN;
    }

    public Long getId()
    {
        return id;
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

    @Override
    public String toString()
    {
        return ">>Librarian Information:\n"+"\tName: "+n+"\n"+"\tFamily Name: "+fN+"\n"+"\tId: "+id+"\n"+"\tBorrowed Books: "+nOBB+"\n"+"\tReturned Book: "+nORB;
    }

}
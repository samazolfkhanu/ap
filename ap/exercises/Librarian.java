package ap.exercises;

import java.io.Serializable;
import java.util.Random;

public class Librarian implements Serializable
{
    private String n;
    private String fN;
    private Long id;
    private int nOBB;
    private int nORB;

    public Librarian(){}

    public Librarian(String n,String fN)throws InvalidInputException
    {
        Random r=new Random();
        if(n!=null && fN!=null )
        {
            this.n=n;
            this.fN=fN;
            this.id=r.nextLong(1000);
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

    public Long getID()
    {
        return id;
    }

    public void setnOBB()
    {
        nOBB++;
    }
    public int increasenOBB()
    {
        return nOBB;
    }

    public void increaseORB()
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
        return "\n[\nName: "+n+"\nFamily Name: "+fN+"\nID: "+id+"\n]\n";
    }

}
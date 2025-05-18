package ap.exercises.ex3;
import java.io.Serializable;

public class Book implements Serializable
{
    
    private String bN;
    private String aN;
    private int nP;
    private int pY;
    private boolean trust=false;
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


    public void setTrust()
    {
        trust=true;
    }

    public boolean getTrust()
    {
        return trust;
    }

    public String getBookName()
    {
        return bN;
    }
    public void setBookName(String bN) throws InvalidInputException
    {
        if(bN!=null)
            this.bN=bN;
        else
            throw new InvalidInputException("invalid input!");
    }

    public String getAuthorName()
    {
        return aN;
    }
    public void setAuthorName(String aN) throws InvalidInputException
    {
        if(aN!=null)
            this.aN=aN;
        else
            throw new InvalidInputException("invalid input!");
    }

    public int getNumPage()
    {
        return nP;
    }
    public void setNumPage(int nP)throws InvalidInputException
    {
        if(nP>0)
            this.nP=nP;
        else
            throw new InvalidInputException("invalid input!");
    }

    public int getPublishedYear()
    {
        return pY;
    }
    public void setPublishedYear(int pY) throws InvalidInputException
    {
        if(pY>0)
            this.pY=pY;
        else
            throw new InvalidInputException("invalid input!");

    }

    @Override
    public String toString()
    {
        return "book name: "+this.bN+"\tauthor name: "+aN+"\tnumber of pages: "+nP+"\tyear of published:"+pY+"\t"+"is borrowed?"+trust+"\n";
    }
}

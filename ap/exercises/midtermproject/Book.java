package ap.exercises.midtermproject;

import java.io.Serializable;

public class Book implements Serializable , Comparable<Book>,HashId<Book>
{
    private static final long sv=1L;
    private String bN;
    private String aN;
    private int nP;
    private int pY;
    private Long id;
    private int  borrowCount;
    public Book(String bN,String aN,int nP,int pY,Long id)throws InvalidInputException
    {
        if(bN!=null && aN!=null && nP>0 && pY>0 && id>0 )
        {
            this.bN = bN;
            this.aN = aN;
            this.nP = nP;
            this.pY = pY;
            this.borrowCount=0;
            this.id=id;
        }
        else {
            throw new InvalidInputException("Error: invalid input!");
        }
    }

    public int getBorrowCount()
    {
        return borrowCount;
    }
    public void increseBorrowCount()
    {
        borrowCount++;
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
    public Long getId()
    {
        return id;
    }

    @Override
    public int compareTo(Book other)
    {
        return Integer.compare(this.borrowCount,other.getBorrowCount());
    }

    @Override
    public String toString()
    {
        return ">>Book Information:"+"\n"+"\tBook Name: "+this.bN+"\n"+"\tAuthor Name: "+aN+"\n"+"\tNumber Of Pages: "+nP+"\n"+"\tYear Of Published:"+pY+"\n"+"\tNumber Of Borrowd Time: "+borrowCount;
    }
}

package ap.exercises.ex7;

import java.io.Serializable;
import java.util.function.Function;

public class Book implements Serializable , Comparable<Book>,HashId
{
    private static final long sv=1L;
    private Long id;
    private String bN;
    private String aN;
    private int nP;
    private int pY;
    private int  borrowCount;
    public String toTabSeparatedString() {
        return String.join("\t",
                id.toString(),
                bN,
                aN,
                Integer.toString(nP),
                Integer.toString(pY),
                Integer.toString(borrowCount)
        );
    }
    public static Book fromTabSeparated(String line) throws InvalidInputException {
        String[] parts = line.split("\t");
        if (parts.length != 6) {
            throw new InvalidInputException("Invalid tab-separated book data");
        }

        Book b=new Book(
                parts[1],  // bookName
                parts[2],  // authorName
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4]),
                Long.parseLong(parts[0])
        );
        b.setBorrowCount(Integer.parseInt(parts[5]));
        return b;
    }
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
    public void setBorrowCount(int i)
    {
        if(i>=0)
            this.borrowCount=i;
        else
            throw new IllegalArgumentException("Invalid borrowcount!");
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
        return ">>Book Information:"+"\n"+"\tBook Name: "+this.bN+"\n"+"\tAuthor Name: "+aN+"\n"+"\tNumber Of Pages: "+nP+"\n"+"\tYear Of Published:"+pY+"\n"+"\tNumber Of Borrowd Time: "+borrowCount+"\n";
    }
}

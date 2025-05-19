package ap.exercises.midtermproject;

import java.io.Serializable;

public class Request implements Serializable,HashId {
    private static final long sv=1L;
    private Book book;
    private Student student;
    private Long id;
    private static Long idCount=0L;
    private Librarian issuedBy;
    private Librarian recivedBy;

    public Request(Book book,Student student ,Librarian issuedBy)
    {
        idCount+=1;
        this.book=book;
        this.student=student;
        this.id=idCount;
        this.issuedBy=issuedBy;
    }

    @Override
    public Long getId()
    {
        return id;
    }

    public Student getStudent()
    {
        return student;
    }

    public Book getBook()
    {
        return book;
    }

    public Librarian getIssuedBy()
    {
        return issuedBy;
    }
    public Librarian getRecivedBy()
    {
        return recivedBy;
    }
    public void setRecivedBy(Librarian l)
    {
        if(l!=null)
            this.recivedBy=l;
        else
            throw new InvalidInputException("Invalid Input!");
    }
    @Override
    public String toString()
    {
        return ">>Request Ifnormation: \n"+"\t"+book+"\n"+"\t"+student+"\n"+"\tId: "+id+"\n"+"\tIssuedBy: "+issuedBy;
    }
}

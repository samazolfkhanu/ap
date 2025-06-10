package ap.exercises.ex7;

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
    public String toTabSeparatedString()
    {
        return String.join(book.toTabSeparatedString(), student.toTabSeparatedString()
        ,String.valueOf(id), issuedBy.toTabSeparatedString(), recivedBy.toTabSeparatedString());
    }

    public static Request fromTabSeparated(String line) throws InvalidInputException
    {
        String[] parts=line.split("\t");
        Request r=new Request(Book.fromTabSeparated(parts[0]),Student.fromTabSeparated(parts[1]),Librarian.fromTabSeparated(parts[3]));
        r.setRecivedBy(Librarian.fromTabSeparated(parts[4]));
        r.setid(Long.parseLong(parts[2]));
        return r;
    }

    @Override
    public Long getId()
    {
        return id;
    }
    public void setid(Long id)
    {
        if(id>0)
            this.id= id;
        else
            throw new IllegalArgumentException("Invalid Input!");
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

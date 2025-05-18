package ap.exercises.midtermproject;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan implements Serializable,  HashId<Loan>
{
    private static final long sv=1L;
    private Book b;
    private Student s;
    private Librarian issuedBy;
    private Librarian receivedBy;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Long id;
    private static Long idCount= 0L;

    public Loan(Book b,Student s, Librarian issuedBy,LocalDate issueDate, LocalDate dueDate)
    {
        idCount+=1;
        this.b=b;
        this.s=s;
        this.issuedBy=issuedBy;
        this.receivedBy=receivedBy;
        this.issueDate=issueDate;
        this.dueDate=dueDate;
        this.returnDate=null;
        id=idCount;
    }

    public void setReceivedBy(Librarian l)
    {
        if(l!=null)
            this.receivedBy=l;
        else
            throw new InvalidInputException("Invalid Input!");
    }

    public boolean isOverDue()
    {
        if(returnDate!=null)
            return returnDate.isAfter(dueDate);
        else
            return LocalDate.now().isAfter(dueDate);
    }

    public long getDelayDays()
    {
        LocalDate c=(returnDate!=null?returnDate:LocalDate.now());
        if(c.isAfter(dueDate))
            return ChronoUnit.DAYS.between(dueDate,c);

        return 0;

    }

    public Book getB() {
        return b;
    }

    public Librarian getIssuedBy() {
        return issuedBy;
    }

    public Librarian getReceivedBy() {
        return receivedBy;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public Student getS() {
        return s;
    }

    public void setIssueDate(LocalDate l)
    {
        if(l!=null) {
            this.issueDate = l;
            dueDate=issueDate.plusDays(30);
        }
        else
            throw new NullPointerException("Invalid input!");
    }
    public void setReturnDate(LocalDate l)
    {
        if(l!=null)
            this.returnDate=l;
        else
            throw new NullPointerException("Invalid input!");
    }

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return ">>Loan Information: "+"\n"+"\t"+b.toString()+"\n"+"\t"+s.toString()+"\n"+"\t"+"IssuedBy: "+issuedBy+"\n"+"\t"+"ReceivedBy: "+receivedBy+"\n"+"\t"+"Id: "+id+"\n"+"\t"+"IssueDate: "+issueDate+"\n"+"\tdueDate"+dueDate+"\n"+"\treturnDate: "+(returnDate!=null ?returnDate:"Not Yet Returned!");
    }
}

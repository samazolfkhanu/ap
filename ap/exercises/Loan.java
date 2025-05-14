package ap.exercises;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan implements Serializable
{
    private Book b;
    private Student s;
    private Librarian issuedBy;
    private Librarian receivedBy;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(Book b,Student s, Librarian issuedBy, Librarian receivedBy, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate)
    {
        this.b=b;
        this.s=s;
        this.issuedBy=issuedBy;
        this.receivedBy=receivedBy;
        this.issueDate=issueDate;
        this.dueDate=dueDate;
        this.returnDate=returnDate;
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
    public String toString()
    {
        return "INFORMATION: "+"\n"+"Book: \n"+b.toString()+"Student: \n"+s.toString()+"IssuedBy: "+issuedBy+"\nReceivedBy: "+receivedBy+"\nIssueDate: "+issueDate+"\ndueDate"+dueDate+"\n"+"\nreturnDate: "+(returnDate!=null ?returnDate:"Not Yet Returned!");
    }
}

package ap.exercises.finalproject;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan implements Serializable
{
    private int idCount=1;
    private Book book;
    private Student student;
    private int id;
    private LocalDate borrowRequestDate;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Librarian issuer;
    private Librarian receiver;
    public Loan(Book book,Student student)throws InvalidEntrance
    {
        if(book!=null && student!=null)
        {
            this.book=book;
            this.student=student;
            this.id=idCount;
            this.dueDate=null;
            this.borrowRequestDate =LocalDate.now();
            this.issueDate=null;
            this.returnDate=null;
            this.issuer=null;
            this.receiver=null;
            idCount++;
        }
        else
        {
            throw new InvalidEntrance("Invalid Inputs! <300>");
        }
    }

    public long getDelayDays()
    {
        return Math.max(0, ChronoUnit.DAYS.between(dueDate,returnDate));
    }

    public Book getBook()
    {
        return book;
    }
    public Student getStudent()
    {
        return student;
    }
    public int getId()
    {
        return id;
    }
    public LocalDate getIssueDate()
    {
        return issueDate;
    }
    public LocalDate getReturnDate()
    {
        return returnDate;
    }

    public void setIssueDate()
    {
        this.issueDate=LocalDate.now();
        setDueDate();
    }
    private void setDueDate()
    {
        this.dueDate=issueDate.plusDays(30);
    }
    public LocalDate getDueDate()
    {
        return dueDate;
    }
    public void setIssuer(Librarian l)
    {
        this.issuer=l;
    }
    public void setReceiver(Librarian l)
    {
        this.receiver=l;
    }

    public Librarian getIssuer()
    {
        return issuer;
    }
    public Librarian getReceiver()
    {
        return receiver;
    }
    public void setReturnDate()
    {
        this.returnDate=LocalDate.now();
    }
    public LocalDate getBorrowRequestDate()
    {
        return borrowRequestDate;
    }

    public String toString()
    {
        return "Loan Info:\n" +
                "ID: "+id+
                "\n\t"+book+
                "\n\t"+student+
                "\nIssueDate: "+issueDate
                +"\nReturnDate: "+returnDate
                +"\nDueDate: "+dueDate
                +"\nIssuer: "+issuer
                +"\nReceiver: "+receiver;
    }

}

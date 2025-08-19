package ap.exercises.finalproject;

import java.time.LocalDate;

public class Loan
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
        this.dueDate=LocalDate.now();
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

    public String toString()
    {
        return "Loan Info:\n" +
                "\t"+book+
                "\n\t"+student+
                "\nIssueDate: "+issueDate
                +"\nReturnDate: "+returnDate;
    }

}

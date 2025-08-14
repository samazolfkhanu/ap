package ap.exercises.finalproject;

import java.time.LocalDate;

public class Loan
{
    private int idCount=1;
    private Book book;
    private Student student;
    private int id;
    private LocalDate borrowRequest;
    private LocalDate returnRequest;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(Book book,Student student)throws InvalidEntrance
    {
        if(book!=null && student!=null)
        {
            this.book=book;
            this.student=student;
            this.id=idCount;
            this.dueDate=null;
            this.borrowRequest=LocalDate.now();
            this.returnRequest=null;
            this.issueDate=null;
            this.returnDate=null;
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
    public String getIssueDate()
    {
        return issueDate.toString();
    }
    public String getReturnDate()
    {
        return returnDate.toString();
    }

    public void setDueDate()
    {
        this.dueDate=LocalDate.now();
    }
    public String getDueDate()
    {
        return dueDate.toString();
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

package ap.exercises.ex7;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan implements Serializable,  HashId {
    private static final long sv = 1L;
    private Book b;
    private Student s;
    private Librarian issuedBy;
    private Librarian receivedBy;
    private String issueDate;
    private String dueDate;
    private String returnDate;
    private Long id;
    private static Long idCount = 0L;

    public Loan(Book b, Student s, Librarian issuedBy, String issueDate, String dueDate) {
        idCount += 1;
        this.b = b;
        this.s = s;
        this.issuedBy = issuedBy;
        this.receivedBy =null;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        id = idCount;
    }

    public String toTabSeparatedString(){
        return String.join("\t",b.toTabSeparatedString(),s.toTabSeparatedString(),
                issuedBy.toTabSeparatedString(),
                receivedBy.toTabSeparatedString(),
                String.valueOf(id),
                String.valueOf(issueDate),
                String.valueOf(dueDate),
                String.valueOf(returnDate));
    }
    public static Loan fromTabSeparated(String line) throws InvalidInputException
    {
        String[] parts = line.split("\t");
        Loan l=new Loan(Book.fromTabSeparated(parts[0]),Student.fromTabSeparated(parts[1]),
                Librarian.fromTabSeparated(parts[2]),parts[5],
                parts[6]);
        l.setReceivedBy(Librarian.fromTabSeparated(parts[3]));
        l.setId(Long.parseLong(parts[4]));
        l.setReturnDate(LocalDate.parse(parts[7]));
        return l;
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
        LocalDate l=LocalDate.parse(returnDate);
        LocalDate l2=LocalDate.parse(dueDate);
        if(l!=null)
            return l.isAfter(l2);
        else
            return LocalDate.now().isAfter(l2);
    }

    public void setId(Long id)
    {
        if(id>0)
            this.id=id;
        else
            throw new IllegalArgumentException("id can not be negative!");
    }
    public long getDelayDays()
    {
        LocalDate l=LocalDate.parse(returnDate);
        LocalDate l2=LocalDate.parse(dueDate);
        LocalDate c=(l!=null?l:LocalDate.now());
        if(c.isAfter(l2))
            return ChronoUnit.DAYS.between(l2,c);

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



    public LocalDate getReturnDate() {
        return LocalDate.parse(dueDate);
    }

    public Student getS() {
        return s;
    }

    public void setReturnDate(LocalDate l)
    {
        if(l!=null)
            this.returnDate=l.toString();
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
        return ">>Loan Information: "+"\n"+"\t"+b.toString()+"\n"+"\t"+s.toString()+"\n"+"\t"+"IssuedBy: \n"+issuedBy+"\n"+"\t"+"ReceivedBy: \n"+receivedBy+"\n"+"\t"+"Id: "+id+"\n"+"\t"+"IssueDate: "+issueDate+"\n"+"\tdueDate"+dueDate+"\n"+"\treturnDate: "+(returnDate!=null ?returnDate:"Not Yet Returned!");
    }
}

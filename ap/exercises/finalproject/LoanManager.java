package ap.exercises.finalproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanManager
{
    private BookHandler bookHandler;
    FileHandling<Loan> bF;
    FileHandling<Loan> rF;
    FileHandling<Loan> lF;
    FileHandling<Loan> hF;
    private List<Loan> borrowRequest;
    private List<Loan> loans;
    private List<Loan> history;
    private List<Loan> returnRequest;

    public LoanManager()
    {
        bookHandler=new BookHandler();
        bF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/BorrowRequest.txt");
        lF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Loans.txt");
        rF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/ReturnRequest.txt");
        hF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/History.txt");
        borrowRequest=new ArrayList<>();
        returnRequest=new ArrayList<>();
        loans=new ArrayList<>();
        history=new ArrayList<>();
    }

    public List<Loan> getBorrowRequest()
    {
        borrowRequestList();
        return borrowRequest;
    }

    public void getLoans()
    {
        if(!loans.isEmpty())
            loans.clear();
        loans=lF.readFromFile(Loan.class);
    }

    public void addToLoanList(int id,Librarian librarian)
    {
        getLoans();
        borrowRequestList();
        for(Loan l:borrowRequest)
        {
            if(l.getId()==id)
            {
                bookHandler.editBookState(l.getBook(),"borrowed");
                l.getBook().setState("borrowed");
                l.setIssueDate();
                l.setIssuer(librarian);
                if(loans.contains(l)) {
                    System.out.println("Loan Has Already Added!");
                    break;
                }
                else {
                    lF.writeInFile(l);
                    System.out.println("Loan Was Added Successfully!");
                    break;
                }
            }

        }
    }

    public void getHistory()
    {
        if(!history.isEmpty())
            history.clear();
        history=hF.readFromFile(Loan.class);
    }

    public int totalLoanPerStudent(String username,String id)
    {
        getLoans();
        int count=0;
        getHistory();
        for(Loan l:history)
        {
            if(l.getStudent().getStudentId().equals(id) && l.getStudent().getUsername().equals(username))
                count++;
        }
        for (Loan l:loans)
        {
            if (l.getStudent().getStudentId().equals(id) && l.getStudent().getUsername().equals(username))
                count++;
        }
        return count;
    }

    public void historyPerStudent(String username,String id)
    {
        getLoans();
        getHistory();
        for(Loan l:history)
        {
            if(l.getStudent().getStudentId().equals(id) && l.getStudent().getUsername().equals(username))
                System.out.println(l);
        }
        for (Loan l:loans)
        {
            if (l.getStudent().getStudentId().equals(id) && l.getStudent().getUsername().equals(username))
                System.out.println(l);
        }
    }

    public int allNotReturnedBookPerStudent(String username,String id)
    {
        int count=0;
        getLoans();
        for (Loan l:loans)
        {
            if(l.getStudent().getStudentId().equals(id) && l.getStudent().getUsername().equals(username))
                count++;
        }
        return count;
    }

    public int totalOverdueLoansPerStudent(String username,String id) {
        getHistory();
        int count = 0;
        for (Loan l : history) {
            if (l.getStudent().getStudentId().equals(id) && l.getStudent().getUsername().equals(username)) {
                if (l.getReturnDate().isAfter(l.getDueDate()))
                    count++;
            }
        }
        return count;
    }

    public void returnRequest(Student s,Book b)
    {
        getLoans();
        getReturnRequestList();
        for(Loan l:loans)
        {
            if(l.getStudent().getUsername().equals(s.getUsername())
                    && l.getBook().getName().equals(b.getName())
                    && l.getBook().getAuthor().equals(b.getAuthor()))
            {
                if(returnRequest.contains(l))
                {
                    loans.remove(l);
                    System.out.println("Request has Already Added!");
                }
                else
                {
                    rF.writeInFile(l);
                    loans.remove(l);
                }
            }
        }
        updateLoanList(loans);
    }

    public void updateLoanList(List<Loan> l)
    {
        rF.clearFile();
        for(Loan loan:l)
            rF.writeInFile(loan);

    }

    public void borrowRequestList()
    {
        if(!borrowRequest.isEmpty())
            borrowRequest.clear();
        borrowRequest= bF.readFromFile(Loan.class);
    }

    public void getReturnRequestList()
    {
        if(!returnRequest.isEmpty())
            returnRequest.clear();
        returnRequest=rF.readFromFile(Loan.class);
    }

    public void borrowRequest(Book book,Student student) throws InvalidEntrance {
        borrowRequestList();
        if(student.getPermission().equalsIgnoreCase("available"))
        {
            Loan l=new Loan(book,student);
            bookHandler.editBookState(l.getBook(),"Reserved");
            l.getBook().setState("reserved");
            if(borrowRequest.contains(l))
                System.out.println("Request Already Added!");
            else
            {
                bF.writeInFile(l);
                System.out.println("Request Was Added Successfully!");
            }
        }
        else
        {
            System.out.println("Sorry! You Are Banned.");
        }
    }

    public void removeBanStudent(String username,String id)
    {
        getBorrowRequest();
        for(Loan loan:borrowRequest)
        {
            if(loan.getStudent().getUsername().equals(username) && loan.getStudent().getStudentId().equals(id))
                borrowRequest.remove(loan);
        }
        updateLoanList(borrowRequest);
    }

    public void getReturnRequest()
    {
        getReturnRequestList();
        for(Loan l:returnRequest)
            System.out.println(l);
    }

    public void addToHistory(int id,Librarian librarian)
    {
        getHistory();
        getReturnRequest();
        for(Loan l:returnRequest)
        {
            if(l.getId()==id)
            {
                l.setReceiver(librarian);
                bookHandler.editBookState(l.getBook(),"Available");
                if(history.contains(l))
                    System.out.println("Request Has Already Added To History!");
                else
                {
                    hF.writeInFile(l);
                }
                returnRequest.remove(l);
            }
        }

    }

    public void librarianHistory(String username)
    {
        int issue=0;
        int recieve=0;
        getHistory();
        for(Loan l:history)
        {
            if(l.getIssuer().getUsername().equals(username))
                issue++;
            if(l.getReceiver().getUsername().equals(username))
                recieve++;
        }
        System.out.println("Number Of Issues: "+issue);
        System.out.println("Number of ReceiveProcess: "+recieve);
    }

}

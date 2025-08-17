package ap.exercises.finalproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanManager
{
    FileHandling<Loan> bF;
    FileHandling<Loan> rF;
    FileHandling<Loan> lF;
    private List<Loan> borrowRequest;
    private List<Loan> loans;
    private List<Loan> returnRequest;

    public LoanManager()
    {
        bF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/BorrowRequest.txt");
        lF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Loans.txt");
        rF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/ReturnRequest.txt");
        borrowRequest=new ArrayList<>();
        returnRequest=new ArrayList<>();
        loans=new ArrayList<>();
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

    public void addToLoanList(int id)
    {
        getLoans();
        borrowRequestList();
        for(Loan l:borrowRequest)
        {
            if(l.getId()==id)
            {
                l.getBook().setState("borrowed");
                l.setIssueDate();
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

    public void borrowRequestList()
    {
        if(!borrowRequest.isEmpty())
            borrowRequest.clear();
        borrowRequest= bF.readFromFile(Loan.class);
    }

    public void borrowRequest(Book book,Student student) throws InvalidEntrance {
        borrowRequestList();
        Loan l=new Loan(book,student);
        if(borrowRequest.contains(l))
            System.out.println("Request Already Added!");
        else
        {
            bF.writeInFile(l);
            System.out.println("Request Was Added Successfully!");
        }
    }


}

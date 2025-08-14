package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.List;

public class RequestHandler
{
    FileHandling<Loan> bF;
    FileHandling<Loan> rF;
    private List<Loan> borrowRequest;
    private List<Loan> returnRequest;

    public RequestHandler()
    {
        bF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/BorrowRequest.txt");
        borrowRequest=new ArrayList<>();
        returnRequest=new ArrayList<>();
    }

    public void borrowRequest(Book book,Student student) throws InvalidEntrance {
        if(!borrowRequest.isEmpty())
            borrowRequest.clear();
        borrowRequest= bF.readFromFile(Loan.class);
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

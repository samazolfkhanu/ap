package ap.exercises.finalproject;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

    public LoanManager() {
        bookHandler=new BookHandler();
        bF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/BorrowRequests.json");
        lF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Loans.json");
        rF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/ReturnRequests.json");
        hF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/History.json");
        borrowRequest=new ArrayList<>();
        returnRequest=new ArrayList<>();
        loans=new ArrayList<>();
        history=new ArrayList<>();
    }

    public List<Loan> getBorrowRequest() {
        borrowRequestList();
        return borrowRequest;
    }

    public void getLoans() {
        if(loans!=null && !loans.isEmpty())
            loans.clear();
        loans=lF.readFromFile(Loan.class);
    }

    public void updateBorrowRequestList(List<Loan> l) {
        bF.clearFile();
        for(Loan loan:l)
        {
            bF.writeInFile(loan,Loan.class);
        }
    }

    public void addToLoanList(int id,Librarian librarian) {
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
                if(loans!=null && loans.contains(l)) {
                    System.out.println("Loan Has Already Added!");
                    break;
                }
                else {
                    lF.writeInFile(l,Loan.class);
                    borrowRequest.remove(l);
                    updateBorrowRequestList(borrowRequest);
                    System.out.println("Loan Was Added Successfully!");
                    break;
                }
            }

        }
    }

    public void getHistory() {
        if(history!=null && !history.isEmpty())
            history.clear();
        history=hF.readFromFile(Loan.class);
    }

    public int totalLoanPerStudent(String username,String id) {
        getLoans();
        int count=0;
        getHistory();
        if(history!=null) {
            count=history.stream()
                    .filter(x->x.getStudent().getStudentId().equals(id) &&
                            x.getStudent().getUsername().equals(username))
                    .collect(Collectors.collectingAndThen(Collectors.counting(),Long::intValue));
        }
        if(loans!=null) {
            count+=loans.stream()
                    .filter(x->x.getStudent().getStudentId().equals(id) &&
                            x.getStudent().getUsername().equals(username))
                    .collect(Collectors.collectingAndThen(Collectors.counting(),Long::intValue));
        }
        return count;
    }

    public void historyPerStudent(String username,String id) {
        getLoans();
        getHistory();
        if(loans!=null) {
            loans.stream()
                    .filter(x->x.getStudent().getStudentId().equals(id) && x.getStudent().getUsername().equals(username))
                    .forEach(System.out::println);
        }
        if(history!=null) {
            history.stream()
                    .filter(x->x.getStudent().getStudentId().equals(id) && x.getStudent().getUsername().equals(username))
                    .forEach(System.out::println);
        }
    }

    public int allNotReturnedBookPerStudent(String username,String id) {
        int count=0;
        getLoans();
        if(loans!=null) {
            count+=loans.stream()
                    .filter(x->x.getStudent().getStudentId().equals(id) && x.getStudent().getUsername().equals(username))
                    .collect(Collectors.collectingAndThen(Collectors.counting(),Long::intValue));
        }
        return count;
    }

    public int totalOverdueLoansPerStudent(String username,String id) {
        getHistory();
        int count = 0;
        if(history!=null) {
            count+=history.stream()
                    .filter(x->x.getStudent().getStudentId().equals(id) && x.getStudent().getUsername().equals(username))
                    .collect(Collectors.collectingAndThen(Collectors.counting(),Long::intValue));
        }
        return count;
    }

    public void returnRequest(Student s,Book b) {
        getLoans();
        getReturnRequestList();
        for(Loan l:loans) {
            if(l.getStudent().getUsername().equals(s.getUsername())
                    && l.getBook().getName().equals(b.getName())
                    && l.getBook().getAuthor().equals(b.getAuthor()))
            {
                System.out.println(l);
                if(returnRequest!=null && returnRequest.contains(l)) {
                    loans.remove(l);
                    System.out.println("Request has Already Added!");
                    break;
                }
                else {
                    rF.writeInFile(l,Loan.class);
                    loans.remove(l);
                    System.out.println("Request Added Successfully!");
                    break;
                }
            }
        }
        if(loans.isEmpty())
            lF.clearFile();
        else
            updateLoanList(loans);
    }

    public void updateLoanList(List<Loan> l) {
        rF.clearFile();
        if(!l.isEmpty()) {
            for (Loan loan : l)
                rF.writeInFile(loan,Loan.class);
        }

    }

    public void borrowRequestList() {
        if(borrowRequest!=null && !borrowRequest.isEmpty())
            borrowRequest.clear();
        borrowRequest= bF.readFromFile(Loan.class);
    }

    public void getReturnRequestList() {
        if(returnRequest!=null && !returnRequest.isEmpty())
            returnRequest.clear();
        returnRequest=rF.readFromFile(Loan.class);
    }

    public void borrowRequest(Book book,Student student) throws InvalidEntrance {
        getBorrowRequest();
        if(student.getPermission().equalsIgnoreCase("Active"))
        {
            int maxId=0;
            if(borrowRequest!=null)
            {
                maxId=borrowRequest.stream()
                        .max(Comparator.comparingInt(x->x.getId()))
                        .get()
                        .getId();
            }
            bookHandler.editBookState(book,"reserved");
            Loan l=new Loan(book,student,maxId+1);
            bookHandler.editBookState(l.getBook(),"Reserved");
            l.getBook().setState("reserved");
            if(borrowRequest!=null && borrowRequest.contains(l))
                System.out.println("Request Already Added!");
            else
            {
                bF.writeInFile(l,Loan.class);
                System.out.println("Request Was Added Successfully!");
            }
        }
        else
        {
            System.out.println("Sorry! You Are Banned.");
        }
    }

    public void removeBanStudent(String username) {
        getBorrowRequest();
        if(borrowRequest!=null) {
            for(Loan loan:borrowRequest) {
                if(loan.getStudent().getUsername().equals(username))
                    borrowRequest.remove(loan);
            }
            updateLoanList(borrowRequest);
        }
    }

    public boolean getReturnRequest()
    {
        getReturnRequestList();
        if(returnRequest!=null) {
            for (Loan l : returnRequest) {
                System.out.println(l);
                return true;
            }
        }
        else
            System.out.println("Return Request List Is Empty!");
        return false;
    }

    public void addToHistory(int id,Librarian librarian) {
        getHistory();
        getReturnRequest();
        Iterator<Loan> it=returnRequest.iterator();
        while(it.hasNext()) {
            Loan l=it.next();
            if(l.getId()==id) {
                l.setReturnDate();
                l.setReceiver(librarian);
                bookHandler.editBookState(l.getBook(),"Available");
                if(history!=null && history.contains(l)) {
                    it.remove();
                    System.out.println("Request Has Already Added To History!");
                }
                else {
                    it.remove();
                    hF.writeInFile(l,Loan.class);
                    System.out.println("Book Returned Successfully!");
                }
            }
        }
        if(returnRequest.isEmpty())
            rF.clearFile();
        else
            updateReturnRequestList(returnRequest);
    }

    public void updateReturnRequestList(List<Loan> l) {
        rF.clearFile();
        for(Loan loan:l)
            rF.writeInFile(loan,Loan.class);
    }

    public void librarianHistory(String username){
        int issue=0;
        int recieve=0;
        getHistory();
        if(history!=null) {
            for(Loan l:history) {
                if(l.getIssuer().getUsername().equals(username))
                    issue++;
                if(l.getReceiver().getUsername().equals(username))
                    recieve++;
            }
        }
        System.out.println("Number Of Issues: "+issue);
        System.out.println("Number of ReceiveProcess: "+recieve);
    }
    public void bookHistory(String name,String author) {
        int count=0;
        int c=0;
        long days=0;
        getBorrowRequest();
        if(borrowRequest!=null) {
            count+=borrowRequest.stream()
                    .filter(l->l.getStudent().getName().equalsIgnoreCase(name) && l.getBook().getAuthor().equalsIgnoreCase(author))
                    .collect(Collectors.collectingAndThen(Collectors.counting(),Long::intValue));
        }
        getHistory();
        if(history!=null) {
            for(Loan l:history) {
                if(l.getBook().getName().equalsIgnoreCase(name) && l.getBook().getAuthor().equalsIgnoreCase(author)) {
                    c++;
                    days= ChronoUnit.DAYS.between(l.getIssueDate(),l.getReturnDate());
                }
            }
        }
        System.out.println("Number Of Request for This Book: "+count);
        System.out.println("Number Of Borrowed Time: "+c);
        System.out.println("Average Days Of Borrowing Book: "+(days/c));
    }

    public List<Loan> getTop10LateReturns() {
        getHistory();
        if(history!=null) {
            return history.stream()
                    .filter(x->x.getReturnDate().isAfter(x.getDueDate()))
                    .sorted(Comparator.comparingLong(Loan::getDelayDays))
                    .limit(10)
                    .toList();
        }
        return null;
    }

}

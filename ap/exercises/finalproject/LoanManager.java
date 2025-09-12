package ap.exercises.finalproject;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class LoanManager
{
    private BookHandler bookHandler;
    FileHandling<Loan> bF;
    FileHandling<Loan> rF;
    FileHandling<Loan> lF;
    FileHandling<Loan> hF;
    private Map<Integer,Loan> borrowRequest;
    private Map<Integer,Loan> loans;
    private Map<Integer,Loan> history;
    private Map<Integer,Loan> returnRequest;

    public LoanManager() {
        bookHandler=new BookHandler();
        bF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/BorrowRequests.json");
        lF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Loans.json");
        rF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/ReturnRequests.json");
        hF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/History.json");
        borrowRequest=new HashMap<>();
        returnRequest=new HashMap<>();
        loans=new HashMap<>();
        history=new HashMap<>();
    }

    public Map<Integer,Loan> getBorrowRequest() {
        borrowRequestList();
        return borrowRequest;
    }

    public void getLoans() {
        List<Loan> l=new ArrayList<>();
        if(loans!=null && !loans.isEmpty())
            loans.clear();
        l=lF.readFromFile(Loan.class);
        if(l!=null) {
            loans=l.stream()
                    .collect(Collectors.toMap(x->x.getId(),x->x));
        }
    }

    public void updateBorrowRequestList(Map<Integer,Loan> l) {
        bF.clearFile();
        for(Loan loan:l.values())
        {
            bF.writeInFile(loan,Loan.class);
        }
    }

    public void addToLoanList(int id,Librarian librarian) {
        getLoans();
        borrowRequestList();
        for(Loan l:borrowRequest.values())
        {
            if(l.getId()==id)
            {
                bookHandler.editBookState(l.getBook(),"borrowed");
                l.getBook().setState("borrowed");
                l.setIssueDate();
                l.setIssuer(librarian);
                if(loans!=null && loans.containsValue(l)) {
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
        List<Loan> l=new ArrayList<>();
        if(history!=null && !history.isEmpty())
            history.clear();
        l=hF.readFromFile(Loan.class);
        if(l!=null) {
            history=l.stream()
                    .collect(Collectors.toMap(Loan::getId, x->x));
        }
    }

    public int totalLoanPerStudent(String username) {
        getLoans();
        int count=0;
        getHistory();
        if(history!=null) {
            count=history.entrySet().stream()
                    .filter(x->x.getValue().getStudent().getUsername().equals(username))
                    .collect(Collectors.collectingAndThen(Collectors.counting(),Long::intValue));
        }
        if(loans!=null) {
            count+=loans.entrySet().stream()
                    .filter(x->x.getValue().getStudent().getUsername().equals(username))
                    .collect(Collectors.collectingAndThen(Collectors.counting(),Long::intValue));
        }
        return count;
    }

    public void historyPerStudent(String username) {
        getLoans();
        getHistory();
        if(loans!=null) {
            loans.entrySet().stream()
                    .filter(x->x.getValue().getStudent().getUsername().equals(username))
                    .forEach(System.out::println);
        }
        if(history!=null) {
            history.entrySet().stream()
                    .filter(x->x.getValue().getStudent().getUsername().equals(username))
                    .forEach(System.out::println);
        }
    }

    public int allNotReturnedBookPerStudent(String username) {
        int count=0;
        getLoans();
        if(loans!=null) {
            count+=loans.entrySet().stream()
                    .filter(x->x.getValue().getStudent().getUsername().equals(username))
                    .collect(Collectors.collectingAndThen(Collectors.counting(),Long::intValue));
        }
        return count;
    }

    public int totalOverdueLoansPerStudent(String username) {
        getHistory();
        int count = 0;
        if(history!=null) {
            count+=history.entrySet().stream()
                    .filter(x->x.getValue().getStudent().getUsername().equals(username))
                    .collect(Collectors.collectingAndThen(Collectors.counting(),Long::intValue));
        }
        return count;
    }

    public void returnRequest(Student s,Book b) {
        getLoans();
        getReturnRequestList();
        for(Loan l:loans.values()) {
            if(l.getStudent().getUsername().equals(s.getUsername())
                    && l.getBook().getName().equals(b.getName())
                    && l.getBook().getAuthor().equals(b.getAuthor()))
            {
                System.out.println(l);
                if(returnRequest!=null && returnRequest.containsValue(l)) {
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

    public void updateLoanList(Map<Integer,Loan> l) {
        rF.clearFile();
        if(!l.isEmpty()) {
            for (Loan loan : l.values())
                rF.writeInFile(loan,Loan.class);
        }

    }

    public void borrowRequestList() {
        List<Loan> l;
        if(borrowRequest!=null && !borrowRequest.isEmpty())
            borrowRequest.clear();
        l= bF.readFromFile(Loan.class);
        if(l!=null) {
            borrowRequest=l.stream()
                    .collect(Collectors.toMap(Loan::getId, x->x));
        }
    }

    public void getReturnRequestList() {
        List<Loan> l;
        if(returnRequest!=null && !returnRequest.isEmpty())
            returnRequest.clear();
        l=rF.readFromFile(Loan.class);
        if(l!=null) {
            returnRequest=l.stream()
                    .collect(Collectors.toMap(Loan::getId, x->x));
        }
    }

    public void borrowRequest(Book book,Student student) throws InvalidEntrance {
        getBorrowRequest();
        if(student.getPermission().equalsIgnoreCase("Active"))
        {
            int maxId=0;
            if(borrowRequest!=null)
            {
                maxId=borrowRequest.keySet().stream()
                        .max(Integer::compare)
                        .orElse(0);
            }
            bookHandler.editBookState(book,"reserved");
            Loan l=new Loan(book,student,maxId+1);
            bookHandler.editBookState(l.getBook(),"Reserved");
            l.getBook().setState("reserved");
            if(borrowRequest!=null && borrowRequest.containsKey(l))
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
            Iterator<Map.Entry<Integer,Loan>> it=borrowRequest.entrySet().iterator();
            while(it.hasNext())
            {
                Map.Entry<Integer,Loan> m=it.next();
                if(m.getValue().getStudent().getUsername().equals(username))
                    it.remove();

            }
            updateLoanList(borrowRequest);
        }
    }

    public boolean getReturnRequest()
    {
        getReturnRequestList();
        if(returnRequest!=null) {
            for (Loan l : returnRequest.values()) {
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
        Iterator<Map.Entry<Integer,Loan>> it=returnRequest.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer,Loan> l=it.next();
            if(l.getKey()==id) {
                l.getValue().setReturnDate();
                l.getValue().setReceiver(librarian);
                bookHandler.editBookState(l.getValue().getBook(),"Available");
                if(history!=null && history.containsValue(l)) {
                    it.remove();
                    System.out.println("Request Has Already Added To History!");
                }
                else {
                    it.remove();
                    hF.writeInFile(l.getValue(),Loan.class);
                    System.out.println("Book Returned Successfully!");
                }
            }
        }
        if(returnRequest.isEmpty())
            rF.clearFile();
        else
            updateReturnRequestList(returnRequest);
    }

    public void updateReturnRequestList(Map<Integer,Loan> l) {
        rF.clearFile();
        for(Loan loan:l.values())
            rF.writeInFile(loan,Loan.class);
    }

    public void librarianHistory(String username){
        int issue=0;
        int recieve=0;
        getHistory();
        if(history!=null) {
            for(Loan l:history.values()) {
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
            count+=borrowRequest.entrySet().stream()
                    .filter(l->l.getValue().getStudent().getName().equalsIgnoreCase(name) && l.getValue().getBook().getAuthor().equalsIgnoreCase(author))
                    .collect(Collectors.collectingAndThen(Collectors.counting(),Long::intValue));
        }
        getHistory();
        if(history!=null) {
            for(Loan l:history.values()) {
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
            return history.values().stream()
                    .filter(loan -> loan.getReturnDate().isAfter(loan.getDueDate()))
                    .sorted(Comparator.comparingLong(x->x.getDelayDays()))
                    .limit(10)
                    .toList();
        }
        return null;
    }

}

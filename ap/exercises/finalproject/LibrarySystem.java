package ap.exercises.finalproject;

import java.time.LocalDate;
import java.util.List;

public class LibrarySystem {
    private StudentManager studentManager;
    private MenuHandler menuHandler;
    private BookHandler bookHandler;
    private LoanManager loanManager;
    private LibrarianManager librarianManager;
    private ManagerHandler managerHandler;

    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.bookHandler=new BookHandler();
        this.menuHandler = new MenuHandler(this);
        this.librarianManager=new LibrarianManager();
        this.loanManager=new LoanManager();
        this.managerHandler=new ManagerHandler();
    }
    public int getBookCount()
    {
        return bookHandler.getBookCount();
    }

    public void registerStudent(String name, String username, String password) throws InvalidEntrance {
        studentManager.registerStudent(name,username, password);
    }

    public Student authenticateStudent(String username, String password) {
        return studentManager.authenticateUser(username, password);
    }

    public Librarian authenticateLibrarian(String username,String password)
    {
        return librarianManager.authenticateUser(username,password);
    }

    public void editLibrarianInformation(Librarian librarian,String password) throws InvalidEntrance {
        librarianManager.editLibrarianInformation(librarian,password);
    }
    public void editBookInfo(Book book,String input,String part) throws InvalidEntrance {
        switch(part)
        {
            case "name":
                bookHandler.editBookName(book,input);
                break;
            case "author":
                bookHandler.editBookAuthor(book,input);
                break;
            case "publishedYear":
                bookHandler.editBookYear(book,Integer.parseInt(input));
                break;
        }
    }

    public void editStudentInfo(Student s,String input,String part) throws InvalidEntrance {
        switch(part)
        {
            case "name":
                studentManager.editName(input,s);
                break;
            case "id":
                studentManager.editStudentId(input,s);
                break;
            case "password":
                studentManager.editPassword(input,s);
                break;
        }
    }

    public void addBook(String name,String author,int publishedYear,String username) throws InvalidEntrance {
        bookHandler.addBook(name,author,publishedYear);
        librarianManager.increaseLibrarianBook(username);
    }

    public void borrowBookRequest(Student student,String name,String author,int publishedYear) throws InvalidEntrance {
        Book b=bookHandler.isBookAvailable(name,author,publishedYear);
        if (b!=null)
            loanManager.borrowRequest(b,student);
        else
        {
            System.out.println("Book Is Not Available!");
        }
    }

    public void unbanStudent(String username) {
        studentManager.unbanStudent(username);
    }

    public boolean getBorrowRequestList() {
        LocalDate now=LocalDate.now();
        List<Loan> loans=loanManager.getBorrowRequest();
        if(loans!=null)
        {
            for(Loan l:loans) {
                if(l.getBorrowRequestDate().getMonthValue()==now.getMonthValue() && l.getBorrowRequestDate().getYear()==now.getYear())
                {
                    if(l.getBorrowRequestDate().getDayOfMonth()==now.getDayOfMonth())
                        System.out.println(l);
                }
            }
            return true;
        }
        return false;
    }

    public void studentHistory(String username,String id)
    {
        System.out.println("---Student History---");
        System.out.println("All Loans Information:\n");
        loanManager.historyPerStudent(username,id);
        System.out.println("Number Of Loans:"+loanManager.totalLoanPerStudent(username,id));
        System.out.println("Number Of Not Returned Books: "+loanManager.allNotReturnedBookPerStudent(username,id));
        System.out.println("Number Of Overdue Loans: "+loanManager.totalOverdueLoansPerStudent(username,id));

    }
    public void addToLoans(int id,Librarian librarian)
    {
        loanManager.addToLoanList(id,librarian);
    }

    public void returnBookRequest(Student student,String name,String author,int publishedYear)
    {
        loanManager.returnRequest(student,bookHandler.searchBook(name,author,publishedYear));
    }

    public void displayAvailableBooks() {
        bookHandler.displayAvailableBooks();
    }

    public void searchBookByGuest(String name)
    {
        bookHandler.searchBookByGuest(name);
    }
    public Book searchBook(String name,String author,int publishedYear)
    {
        return bookHandler.searchBook(name,author,publishedYear);
    }

    public void start(){
        menuHandler.displayMainMenu();
    }

    public static void main(String[] args){
        LibrarySystem system = new LibrarySystem();
        system.start();
    }
    public void registerManager(String username,String password) throws InvalidEntrance {
        managerHandler.registerManager(username,password);
    }
    public Librarian getAlibrarian(String username)
    {
        return librarianManager.getAUser(username);
    }
    public Student getAStudent(String username)
    {
        return studentManager.getAUser(username);
    }
    public void addLibrarian(String username,String id) throws InvalidEntrance {
        librarianManager.addLibrarian(username,id);
    }

    public boolean getReturnRequest()
    {
        if(loanManager.getReturnRequest())
            return true;
        return false;
    }

    public void addToHistory(int id,Librarian l)
    {
        loanManager.addToHistory(id,l);
    }

    public void banStudent(String username) throws InvalidEntrance {
        studentManager.banStudent(username);
        loanManager.removeBanStudent(username);
    }

    public void librarianHistory(String username)
    {
        librarianManager.librarianHistory(username);
        loanManager.librarianHistory(username);
    }
    public void bookHistory(String name,String author)
    {
        loanManager.bookHistory(name,author);
    }

    public void getTop10LateReturns()
    {
        List<Loan> loans=loanManager.getTop10LateReturns();
        if(loans!=null)
        {
            for(Loan l:loans)
                System.out.println(l);
        }
        else{
            System.out.println("Nothing!");
        }

    }
    public void returnRegisteredUsers(){
        studentManager.returnStudent()
                .forEach(System.out::println);
        librarianManager.returnLibrarian()
                .forEach(System.out::println);
    }

    public Manager authenticateManager(String username,String password)
    {
        return managerHandler.authenticateUser(username,password);
    }
}
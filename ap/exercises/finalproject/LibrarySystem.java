package ap.exercises.finalproject;

import java.time.LocalDate;
import java.util.List;

// LibrarySystem.java
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

    public void registerStudent(String name, String studentId, String username, String password) {
        studentManager.registerStudent(name, studentId, username, password);
    }

    public Student authenticateStudent(String username, String password) {
        return studentManager.authenticateStudent(username, password);
    }

    public Librarian authenticateLibrarian(String username,String password)
    {
        return librarianManager.authenticateLibrarian(username,password);
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
        librarianManager.increaseLibrarianbook(username);
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

    public void unbanStudent(String username,String id) throws InvalidEntrance {
        studentManager.unbanStudent(username,id);
    }

    public void getBorrowRequestList() {
        LocalDate now=LocalDate.now();
        List<Loan> loans=loanManager.getBorrowRequest();
        for(Loan l:loans) {
            if(l.getBorrowRequestDate().getMonthValue()==now.getMonthValue() && l.getBorrowRequestDate().getYear()==now.getYear())
            {
                if(l.getBorrowRequestDate().getDayOfMonth()==now.getDayOfMonth())
                    System.out.println(l);
            }
        }
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

    public BookHandler getBookHandler()
    {
        return bookHandler;
    }

    public void searchBookByGuest(String name)
    {
        bookHandler.searchBookByGuest(name);
    }
    public Book searchBook(String name,String author,int publishedYear)
    {
        return bookHandler.searchBook(name,author,publishedYear);
    }

    public void start() {
        menuHandler.displayMainMenu();
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }

    public Librarian getAlibrarian(String username)
    {
        return librarianManager.getALibrarian(username);
    }
    public Student getAStudent(String username)
    {
        return studentManager.getAStudent(username);
    }
    public void addLibrarian(String username,String id) throws InvalidEntrance {
        librarianManager.addLibrarian(username,id);
    }

    public void getReturnRequest()
    {
        loanManager.getReturnRequest();
    }

    public void addToHistory(int id,Librarian l)
    {
        loanManager.addToHistory(id,l);
    }

    public void banStudent(String username,String Id) throws InvalidEntrance {
        studentManager.banStudent(username,Id);
        loanManager.removeBanStudent(username,Id);
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
        for(Loan l:loans)
            System.out.println(l);

    }
    public void returnRegisteredUsers(){
        studentManager.returnStudent()
                .forEach(System.out::println);
        librarianManager.returnLibrarian()
                .forEach(System.out::println);
    }

    public Manager authenticateManager(String username,String password)
    {
        return managerHandler.authenticateManager(username,password);
    }
}

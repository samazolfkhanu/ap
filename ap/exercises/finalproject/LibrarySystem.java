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

    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.bookHandler=new BookHandler();
        this.menuHandler = new MenuHandler(this);
        this.librarianManager=new LibrarianManager();
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
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

    public void editStudentInformation(Student student) {
        System.out.println("Not implemented.");
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

    public void getBorrowRequestList()
    {
        LocalDate now=LocalDate.now();
        List<Loan> loans=loanManager.getBorrowRequest();
        for(Loan l:loans)
        {
            if(l.getIssueDate().getMonthValue()==now.getMonthValue() && l.getIssueDate().getYear()==now.getYear())
            {
                if(l.getIssueDate().getDayOfMonth()==now.getDayOfMonth())
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

    public void banStudent()
    {

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

}

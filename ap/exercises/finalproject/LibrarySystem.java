package ap.exercises.finalproject;

// LibrarySystem.java
public class LibrarySystem {
    private StudentManager studentManager;
    private MenuHandler menuHandler;
    private BookHandler bookHandler;
    private RequestHandler requestHandler;

    public LibrarySystem() {
        this.studentManager = new StudentManager();
        this.bookHandler=new BookHandler();
        this.menuHandler = new MenuHandler(this);
    }

    public int getStudentCount() {
        return this.studentManager.getStudentCount();
    }

    public void registerStudent(String name, String studentId, String username, String password) {
        studentManager.registerStudent(name, studentId, username, password);
    }

    public Student authenticateStudent(String username, String password) {
        return studentManager.authenticateStudent(username, password);
    }

    public void editStudentInformation(Student student) {
        System.out.println("Not implemented.");
    }

    public void borrowBook(Student student,String name,String author,int publishedYear) throws InvalidEntrance {
        Book b=bookHandler.isBookAvailable(name,author,publishedYear);
        if (b!=null)
            requestHandler.borrowRequest(b,student);
        else
        {
            System.out.println("Book Is Not Available!");
        }
    }

    public void returnBook(Student student) {
        System.out.println("Not implemented.");
    }

    public void displayAvailableBooks() {
        bookHandler.displayAvailableBooks();
    }

    public void start() {
        menuHandler.displayMainMenu();
    }

    public static void main(String[] args) {
        LibrarySystem system = new LibrarySystem();
        system.start();
    }

    public BookHandler getBookHandler()
    {
        return bookHandler;
    }
}

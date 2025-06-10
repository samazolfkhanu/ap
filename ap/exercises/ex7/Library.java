package ap.exercises.ex7;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Library implements Serializable {
    private String name;
    private Map<Long, Book> books;
    private Map<Long, Student> students;
    private Map<Long, Librarian> librarians;
    private Map<Long, Loan> loans;
    private Map<Long, Request> requests;
    private Map<Long, Request> returnRequests;
    private List<Loan> history;
    private StorageHandler<Book> bookStorage;
    private StorageHandler<Student> studentStorage;
    private StorageHandler<Librarian> librarianStorage;
    private StorageHandler<Loan> loanStorage;
    private StorageHandler<Request> requestStorage;

    private static final String CONFIG_FILE = "F:/JavaProject/ap/exercises/midtermproject/Config.txt";
    private static final String BOOKS_TABLE = "books";
    private static final String STUDENTS_TABLE = "students";
    private static final String LIBRARIANS_TABLE = "librarians";
    private static final String LOANS_TABLE = "loans";
    private static final String REQUESTS_TABLE = "requests";
    private static final String RETURN_REQUESTS_TABLE = "return_requests";
    private static final String HISTORY_TABLE = "history";

    public Library(String name) {
        if (name == null) {
            throw new NullPointerException("Name of library cannot be null!");
        }
        this.name = name;
        String storageType = readConfigFile();

        switch (storageType.toLowerCase()) {
            case "tabsplit":
                bookStorage = new TabSplitHandler<>();
                studentStorage = new TabSplitHandler<>();
                librarianStorage = new TabSplitHandler<>();
                loanStorage = new TabSplitHandler<>();
                requestStorage = new TabSplitHandler<>();
                break;
            case "json":
                bookStorage = new JsonHandler<>(Book.class);
                studentStorage = new JsonHandler<>(Student.class);
                librarianStorage = new JsonHandler<>(Librarian.class);
                loanStorage = new JsonHandler<>(Loan.class);
                requestStorage = new JsonHandler<>(Request.class);
                break;
            case "sqlite":
                String dbPath = "library.db";
                bookStorage = new SqliteHandler<>(dbPath,Book.class);
                studentStorage = new SqliteHandler<>(dbPath,Student.class);
                librarianStorage = new SqliteHandler<>(dbPath,Librarian.class);
                loanStorage = new SqliteHandler<>(dbPath,Loan.class);
                requestStorage = new SqliteHandler<>(dbPath,Request.class);
                break;
            default:
                throw new IllegalArgumentException("Invalid storage type in config file");
        }

        // Load existing data
        books = bookStorage.load(BOOKS_TABLE);
        if (books == null) books = new TreeMap<>();

        students = studentStorage.load(STUDENTS_TABLE);
        if (students == null) students = new TreeMap<>();

        librarians = librarianStorage.load(LIBRARIANS_TABLE);
        if (librarians == null) librarians = new TreeMap<>();

        loans = loanStorage.load(LOANS_TABLE);
        if (loans == null) loans = new TreeMap<>();

        requests = requestStorage.load(REQUESTS_TABLE);
        if (requests == null) requests = new TreeMap<>();

        returnRequests = requestStorage.load(RETURN_REQUESTS_TABLE);
        if (returnRequests == null) returnRequests = new TreeMap<>();

        history = new ArrayList<>();
        Map<Long, Loan> historyMap = loanStorage.load(HISTORY_TABLE);
        if (historyMap != null) {
            history.addAll(historyMap.values());
        }
    }

    private String readConfigFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            String line = reader.readLine();
            if (line != null && line.startsWith("storage=")) {
                return line.substring(8).trim();
            }
        } catch (IOException e) {
            System.err.println("Error reading config file: " + e.getMessage());
        }
        return "tabsplit"; // Default storage type
    }
    public void addBook(Book b) {
        if (books.containsValue(b)) {
            System.out.println("Book already have been added!");
        } else {
            if (b != null) {
                books.put(b.getId(),b);
                System.out.println("book added successfully!");
                bookStorage.save(b, "F:/JavaProject/ap/exercises/midtermproject/Books" );
            } else throw new NullPointerException("Field of book can not be null!");
        }
    }
    public void addToHistory(Loan l)
    {
        history.add(l);
        loanStorage.save(l,"F:/JavaProject/ap/exercises/midtermproject/history");
    }
    public void registerStudent(Student s) {
        if (students.containsValue(s))
            System.out.println("Student already have registered!");
        else {
            if (s != null) {
                students.put(s.getId(),s);
                System.out.println("Student added successfully!");
                studentStorage.save(s, "F:/JavaProject/ap/exercises/midtermproject/Students");
                System.out.println(s);
            } else throw new NullPointerException("Field of Student can not be Null!");
        }
    }
    public void registerLibrarian(Librarian l) {
        if (librarians.containsValue(l))
            System.out.println("Librarian already have registered!");
        else {
            if (l != null) {
                librarians.put(l.getId(),l);
                System.out.println("Librarian added successfully");
                librarianStorage.save(l, "F:/JavaProject/ap/exercises/midtermproject/Librarians");
                System.out.println(l);
            } else throw new NullPointerException("Field of Librarian can not be Null!");
        }
    }
    public void addNewBook(Long id) {
        Map<Long,Book> b=getBooks();
        for(Map.Entry<Long,Book> b1:b.entrySet()) {
            if(Objects.equals(b1.getValue().getId(), id)) {
                b1.getValue().increseBorrowCount();
                break;
            }
        }
        bookStorage.clear("F:/JavaProject/ap/exercises/midtermproject/Books");
        for(Map.Entry<Long,Book> b2:b.entrySet()) {
            bookStorage.save(b2.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Books");
        }
    }
    public void addNewStudentit(Long id) {
        Map<Long,Student> b=getStudents();
        for(Map.Entry<Long,Student> b1:b.entrySet()) {
            if(Objects.equals(b1.getValue().getId(), id)) {
                b1.getValue().increaseTrust();
            }
        }
        studentStorage.clear("F:/JavaProject/ap/exercises/midtermproject/Students");
        for(Map.Entry<Long,Student> b2:b.entrySet()) {
            studentStorage.save(b2.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Students");
        }
    }
    public void addNewStudentrt(Long id) {
        Map<Long,Student> b=getStudents();
        for(Map.Entry<Long,Student> b1:b.entrySet()) {
            if(Objects.equals(b1.getValue().getId(), id)) {
                b1.getValue().reduceTrust();
            }
        }
        studentStorage.clear("F:/JavaProject/ap/exercises/midtermproject/Students");
        for(Map.Entry<Long,Student> b2:b.entrySet()) {
            studentStorage.save(b2.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Students");
        }
    }
    public void borrowProccess(String name,Long idi) {
        boolean isF=false;
        Map<Long,Librarian> l=getLibrarians();
        List<Long> l1=new ArrayList<>(l.keySet());
        Random r=new Random();
        Map<Long,Book> m=getBooks();
        Map<Long,Student> students=getStudents();
        for(Map.Entry<Long,Book> book:m.entrySet()) {
            if(book.getValue().getBookName().equalsIgnoreCase(name)) {
                isF=true;
                Long key= l1.get(r.nextInt(l1.size()));
                Request l2=new Request(book.getValue(),students.get(idi),l.get(key));
                if(borrowBookRequest(l2)) {
                    System.out.println(l2);
                }
                else System.out.println("Denied");
            }

        }
    }
    public boolean borrowBookRequest(Request l) {
        if (l.getStudent().getTrust() >= 5) {
            System.out.println("reached to the limit of borrowing book!");
            return false;
        }
        Map<Long,Loan> m2=getLL();
        for(Loan m3:m2.values()) {
            if(m3.getB().getBookName().equalsIgnoreCase(l.getBook().getBookName())) {
                System.out.println("Book is Not Available!");
                return false;
            }
        }
        Map<Long, Loan> m;
        Map<Long,Request> r=getRequestsb();
        if(!r.containsValue(l)) {
            if(!((m=getLL()).isEmpty())) {
                for (Map.Entry<Long, Loan> m1 : m.entrySet()) {
                    if (!m1.getValue().getB().getBookName().equalsIgnoreCase(l.getBook().getBookName())) {
                        System.out.println("Book is Available!");
                        requests.put(l.getIssuedBy().getId(), l);
                        requestStorage.save(l, "F:/JavaProject/ap/exercises/midtermproject/Requests");
                        return true;
                    }
                }
            }
            else {
                requests.put(l.getIssuedBy().getId(), l);
                requestStorage.save(l, "F:/JavaProject/ap/exercises/midtermproject/Requests");
                return true;
            }
        }
        else System.out.println("Book Is Not Available");
        return false;
    }
    public void addTOLoans(Loan l) {
        loans.put(l.getId(),l);
        l.getIssuedBy().increasenOBB();
        l.getB().increseBorrowCount();
        l.getS().increaseTrust();
        loanStorage.save(l,"F:/JavaProject/ap/exercises/midtermproject/Loans");
        System.out.println("Book borrowed successfully");

    }
    public void returnProccess(String bookn) {
        Map<Long,Loan> loan=getLL();
        Map<Long,Librarian> l4=getLibrarians();
        List<Long> l3=new ArrayList<>(l4.keySet());
        System.out.println("hi");
        Random rand=new Random();
        Iterator<Map.Entry<Long,Loan>> it=loan.entrySet().iterator();
        while(it.hasNext()) {
            System.out.println("hii");
            Map.Entry<Long,Loan> m=it.next();
            if(m.getValue().getB().getBookName().equalsIgnoreCase(bookn)) {
                Long k= l3.get(rand.nextInt(l3.size()));
                m.getValue().setReceivedBy(l4.get(k));
                if(returnBookRequest(m.getValue(),l4.get(k))) {
                    LocalDate l=LocalDate.now();
                    m.getValue().setReturnDate(l);
                    addToHistory(m.getValue());
                    System.out.println("Added to pending List!");
                    it.remove();
                    removeFromLoans(loan);
                }
                break;
            }
        }
    }
    public boolean returnBookRequest(Loan l, Librarian libra) {
        Map<Long,Loan> m=getLL();
        for(Map.Entry<Long,Loan> m1:m.entrySet()) {
            if (Objects.equals(m1.getValue().getB().getId(), l.getB().getId())) {
                Request r=new Request(l.getB(),l.getS(),l.getIssuedBy());
                System.out.println(l);
                r.setRecivedBy(libra);
                returnRequests.put(l.getReceivedBy().getId(),r);
                requestStorage.save(r,"F:/JavaProject/ap/exercises/midtermproject/RequestR");
                return true;
            }
            else System.out.println("can not add to pending List!");
        }
        return false;
    }
    public void removeFromLoans(Map<Long,Loan> m) {
        loanStorage.clear("F:/JavaProject/ap/exercises/midtermproject/Loans");
        for(Map.Entry<Long,Loan> m1:m.entrySet()) {
            loanStorage.save(m1.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Loans");
        }
    }

    public List<Book> getborrowdBook() {
        Map<Long,Loan> l=getLL();
        ArrayList<Book> b=new ArrayList<>();
        for(Map.Entry<Long,Loan> m:l.entrySet()) {
            if(m.getValue().getDelayDays()>0)
                b.add(m.getValue().getB());
        }
        return b;
    }
    public Book[] getTopBooks() {
        LocalDate oneYearAgo=LocalDate.now().minusYears(1);
        Map<Long,Loan> m=getHistory();
        List<Book> a=new ArrayList<>();
        for(Loan b:m.values()) {
            if(b.getReturnDate().isAfter(oneYearAgo) || b.getReturnDate().isEqual(oneYearAgo))
                a.add(b.getB());
        }
        Collections.sort(a);
        Book[] b=new Book[10];
        int i=0;
        for(Book book:a) {
            if(i<10) {
                b[i]=book;
                i++;
            }
        }
        return b;
    }
    public Map<Long,Request> getRL() {
        return requestStorage.load("F:/JavaProject/ap/exercises/midtermproject/Requests");
    }
    public Map<Long,Loan> getLL() {
        return loanStorage.load("F:/JavaProject/ap/exercises/midtermproject/Loans");
    }
    public Map<Long,Librarian> getLibrarians() {
        return librarianStorage.load("F:/JavaProject/ap/exercises/midtermproject/Librarians");
    }
    public Map<Long,Book> getBooks() {
        return bookStorage.load("F:/JavaProject/ap/exercises/midtermproject/Books");
    }
    public Map<Long,Student> getStudents() {
        return studentStorage.load("F:/JavaProject/ap/exercises/midtermproject/Students");
    }
    public Map<Long,Request> getReturnRequest() {
        return requestStorage.load("F:/JavaProject/ap/exercises/midtermproject/RequestR");
    }
    public Map<Long,Loan> getHistory() {
        return loanStorage.load("F:/JavaProject/ap/exercises/midtermproject/history");
    }
    public void addNew(Map<Long,Librarian> l) {
        librarianStorage.clear("F:/JavaProject/ap/exercises/midtermproject/Librarians");
        for(Map.Entry<Long,Librarian> m:l.entrySet()) {
            librarianStorage.save(m.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Librarians");
        }
    }
    public void updateRequest (Map<Long,Request> l) {
        if(l.size()==0)
            requestStorage.clear("F:/JavaProject/ap/exercises/midtermproject/Requests");
        else {
            requestStorage.clear("F:/JavaProject/ap/exercises/midtermproject/Requests");
            for(Map.Entry<Long,Request> m:l.entrySet()) {
                requestStorage.save(m.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Requests");
            }
        }
    }
    public void updateRequestR (Request r) {
        Map<Long,Request> l=getRL();
        Iterator<Request> it=l.values().iterator();
        while(it.hasNext()) {
            Request r1=it.next();
            if(Objects.equals(r1.getId(), r.getId())) {
                l.remove(r.getId());
            }
        }
        requestStorage.clear("F:/JavaProject/ap/exercises/midtermproject/RequestR");
        for(Request l1:l.values()) {
            requestStorage.save(l1,"F:/JavaProject/ap/exercises/midtermproject/RequestR");
        }
    }
    public Map<Long,Request> getRequestsb() {
        return requestStorage.load("F:/JavaProject/ap/exercises/midtermproject/RequestR");
    }
    public void listOfBookNR(Long id) {
        Map<Long,Loan> m=getLL();
        boolean isFound=false;
        for(Loan m1:m.values()) {
            if(Objects.equals(m1.getS().getId(), id)) {
                System.out.println(m1);
                isFound = true;
            }
        }
        if(!isFound)
            System.out.println("No borrowing with this Id!");
    }
    public Book searchBook(String name) {
        boolean fb=false;
        Map<Long,Book> b=getBooks();
        for(Book book:b.values()) {
            if(book.getBookName().equalsIgnoreCase(name)) {
                fb=true;
                System.out.println("Book found!");
                return book;
            }
        }
        if(!fb)
            System.out.println("Book not found!");
        return null;
    }
    public void overdueFee(Long id)
    {
        Map<Long,Loan> m=getLL();
        for(Loan m1:m.values()) {
            if(Objects.equals(m1.getS().getId(), id)) {
                System.out.println(m1);
                System.out.println("\n");
                System.out.println("Fee: "+(m1.getDelayDays()*2000)+" $");

            }
        }
    }
}

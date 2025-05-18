package ap.exercises.midtermproject;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Library implements Serializable {
    private String name;
    private Map<Long,Book> books;
    private Map<Long,Student> students;
    private Map<Long,Librarian> librarians;
    private Map<Long,Loan> loan;
    private Map<Long,Request> requests;
    private Map<Long,Request> requestsb;
    private List<Loan> history;
    private FileHandling<Book> f = new FileHandling<>();
    private FileHandling<Student> f1 = new FileHandling<>();
    private FileHandling<Librarian> f2 = new FileHandling<>();
    private FileHandling<Loan> f3 = new FileHandling<>();
    private FileHandling<Request> f4 = new FileHandling<>();
    public Library()
    {}
    public Library(String name) {
        if (name != null) {
            this.name = name;
            books = new TreeMap<>();
            students = new TreeMap<>();
            librarians = new TreeMap();
            loan = new TreeMap<>();
            requests=new TreeMap<>();
            requestsb=new TreeMap<>();
            history=new ArrayList<>();
        } else
            throw new NullPointerException("Name of library can not be null!");
    }

    public void addBook(Book b) {
        if (books.containsValue(b)) {
            System.out.println("Book already have been added!");
        } else {
            if (b != null) {
                books.put(b.getId(),b);
                System.out.println("book added successfully!");
                f.writeToFile(b, "F:/JavaProject/ap/exercises/midtermproject/Books.txt" );
            } else
                throw new NullPointerException("Field of book can not be null!");
        }
    }

    public void registerStudent(Student s) {
        if (students.containsValue(s))
            System.out.println("Student already have registered!");
        else {
            if (s != null) {
                students.put(s.getId(),s);
                System.out.println("Student added successfully!");
                f1.writeToFile(s, "F:/JavaProject/ap/exercises/midtermproject/Students.txt");
                System.out.println(s);
            } else
                throw new NullPointerException("Field of Student can not be Null!");
        }
    }

    public void registerLibrarian(Librarian l) {
        if (librarians.containsValue(l))
            System.out.println("Librarian already have registered!");
        else {
            if (l != null) {
                librarians.put(l.getId(),l);
                System.out.println("Librarian added successfully");
                f2.writeToFile(l, "F:/JavaProject/ap/exercises/midtermproject/Librarians.txt");
                System.out.println(l);
            } else
                throw new NullPointerException("Field of Student can not be Null!");
        }
    }

    public void borrowBookRequest(Request l) {
        if (l.getStudent().getTrust() >= 5) {
            System.out.println("reached to the limit of borrowing book!");
            return ;
        }
        Map<Long,Loan> m2=getLL();
        for(Loan m3:m2.values())
        {
            if(m3.getB().getBookName().equalsIgnoreCase(l.getBook().getBookName()))
            {
                System.out.println("Book is Not Available!");
                return ;
            }
        }
        boolean isfound = false;
        Map<Long, Loan> m;
        if(!((m=getLL()).isEmpty()))
        {
            for (Map.Entry<Long, Loan> m1 : m.entrySet()) {
                if (!m1.getValue().getB().getBookName().equalsIgnoreCase(l.getBook().getBookName())) {
                    isfound = true;
                    System.out.println("Book is Available!");
                    requests.put(l.getIssuedBy().getId(), l);
                    f4.writeToFile(l, "F:/JavaProject/ap/exercises/midtermproject/Requests.txt");
                }
            }
        }
        else
        {
            requests.put(l.getIssuedBy().getId(), l);
            f4.writeToFile(l, "F:/JavaProject/ap/exercises/midtermproject/Requests.txt");
        }
    }

    public void addTOLoans(Loan l)
    {
        loan.put(l.getId(),l);
        l.getIssuedBy().increasenOBB();
        l.getB().increseBorrowCount();
        l.getS().increaseTrust();
        f3.writeToFile(l,"F:/JavaProject/ap/exercises/midtermproject/Loans.txt");
        System.out.println("Book borrowed successfully");

    }

    public boolean returnBookRequest(Loan l, Librarian libra) {
        Map<Long,Loan> m=getLL();
        for(Map.Entry<Long,Loan> m1:m.entrySet())
        {
            if (Objects.equals(m1.getValue().getB().getId(), l.getB().getId())) {
                Request r=new Request(l.getB(),l.getS(),l.getIssuedBy());
                System.out.println(l);
                r.setRecivedBy(libra);
                requestsb.put(l.getReceivedBy().getId(),r);
                f4.writeToFile(r,"F:/JavaProject/ap/exercises/midtermproject/RequestR.txt");
                System.out.println("Added to pending List!");
                return true;
            }
            else
                System.out.println("can not add to pending List!");
        }

        return false;
    }

    public void removeFromLoans(Map<Long,Loan> m)
    {
        f3.clearFile("F:/JavaProject/ap/exercises/midtermproject/Loans.txt");
        for(Map.Entry<Long,Loan> m1:m.entrySet())
        {
            f3.writeToFile(m1.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Loans.txt");
        }
    }

    public List<Book> getborrowdBook()
    {
        Map<Long,Loan> l=getLL();
        ArrayList<Book> b=new ArrayList<>();
        for(Map.Entry<Long,Loan> m:l.entrySet())
        {
            if(m.getValue().getDelayDays()>0)
                b.add(m.getValue().getB());
        }
        return b;
    }

    public Book[] getTopBooks()
    {
        Map<Long,Book> m=getBooks();
        List<Book> a=new ArrayList<>(m.values());
        Collections.sort(a);
        Book[] b=new Book[10];
        int i=0;
        for(Book book:a)
        {
            if(i<9)
            {
                b[i]=book;
                i++;
            }
        }
        return b;
    }

    public void addToHistory(Loan l)
    {
        history.add(l);
        f3.writeToFile(l,"F:/JavaProject/ap/exercises/midtermproject/history.txt");
    }

    public Map<Long,Request> getRL() {
        return f4.readFromFile("F:/JavaProject/ap/exercises/midtermproject/Requests.txt");
    }

    public Map<Long,Loan> getLL() {
        return f3.readFromFile("F:/JavaProject/ap/exercises/midtermproject/Loans.txt");
    }

    public Map<Long,Librarian> getLibrarians() {
        return f2.readFromFile("F:/JavaProject/ap/exercises/midtermproject/Librarians.txt");
    }

    public Map<Long,Book> getBooks() {
        return f.readFromFile("F:/JavaProject/ap/exercises/midtermproject/Books.txt");
    }

    public Map<Long,Student> getStudents() {
        return f1.readFromFile("F:/JavaProject/ap/exercises/midtermproject/Students.txt");
    }
    public Map<Long,Request> getReturnRequest()
    {
        return f4.readFromFile("F:/JavaProject/ap/exercises/midtermproject/RequestR.txt");
    }
    public void addNew(Map<Long,Librarian> l)
    {
        f2.clearFile("F:/JavaProject/ap/exercises/midtermproject/Librarians.txt");
        for(Map.Entry<Long,Librarian> m:l.entrySet())
        {
            f2.writeToFile(m.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Librarians.txt");
        }
    }
    public void updateRequest (Map<Long,Request> l)
    {
        if(l.size()==0)
            f4.clearFile("F:/JavaProject/ap/exercises/midtermproject/Requests.txt");
        else {
            f4.clearFile("F:/JavaProject/ap/exercises/midtermproject/Requests.txt");
            for(Map.Entry<Long,Request> m:l.entrySet())
            {
                f4.writeToFile(m.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Requests.txt");
            }
        }
    }

    public void updateRequestR (Request r)
    {
        Map<Long,Request> l=getRL();
        for(Map.Entry<Long,Request> l1:l.entrySet())
        {
            if(Objects.equals(l1.getValue().getId(), r.getId()))
            {
                l.remove(r.getId());
                break;
            }
        }
        f4.clearFile("F:/JavaProject/ap/exercises/midtermproject/RequestR.txt");
        for(Request l1:l.values())
        {
            f4.writeToFile(l1,"F:/JavaProject/ap/exercises/midtermproject/RequestR.txt");
        }
    }
    public Map<Long,Request> getRequestsb()
    {
        return f4.readFromFile("F:/JavaProject/ap/exercises/midtermproject/RequestR.txt");
    }

    public void addNewBook(Long id)
    {
        Map<Long,Book> b=getBooks();
        for(Map.Entry<Long,Book> b1:b.entrySet())
        {
            if(Objects.equals(b1.getValue().getId(), id))
            {
                b1.getValue().increseBorrowCount();
                break;
            }
        }
        f.clearFile("F:/JavaProject/ap/exercises/midtermproject/Books.txt");
        for(Map.Entry<Long,Book> b2:b.entrySet())
        {
            f.writeToFile(b2.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Books.txt");
        }
    }

    public void addNewStudentit(Long id)
    {
        Map<Long,Student> b=getStudents();
        for(Map.Entry<Long,Student> b1:b.entrySet())
        {
            if(Objects.equals(b1.getValue().getId(), id))
            {
                b1.getValue().increaseTrust();
            }
        }
        f1.clearFile("F:/JavaProject/ap/exercises/midtermproject/Students.txt");
        for(Map.Entry<Long,Student> b2:b.entrySet())
        {
            f1.writeToFile(b2.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Students.txt");
        }
    }

    public void addNewStudentrt(Long id)
    {
        Map<Long,Student> b=getStudents();
        for(Map.Entry<Long,Student> b1:b.entrySet())
        {
            if(Objects.equals(b1.getValue().getId(), id))
            {
                b1.getValue().reduceTrust();
            }
        }
        f1.clearFile("F:/JavaProject/ap/exercises/midtermproject/Students.txt");
        for(Map.Entry<Long,Student> b2:b.entrySet())
        {
            f1.writeToFile(b2.getValue(),"F:/JavaProject/ap/exercises/midtermproject/Students.txt");
        }
    }

    
    public void listOfBookNR(Long id)
    {
        Map<Long,Loan> m=getLL();
        boolean isFound=false;
        for(Loan m1:m.values())
        {
            if(Objects.equals(m1.getS().getId(), id)) {
                System.out.println(m1);
                isFound = true;
            }
        }
        if(!isFound)
            System.out.println("No borrowing with this Id!");
    }

    public Book searchBook(String name)
    {
        boolean fb=false;
        Map<Long,Book> b=getBooks();
        for(Book book:b.values())
        {
            if(book.getBookName().equalsIgnoreCase(name))
            {
                fb=true;
                System.out.println("Book found!");
                return book;
            }
        }
        if(!fb)
            System.out.println("Book not found!");
        return null;
    }

    public void borrowProccess(String name,Long idi)
    {
        Map<Long,Librarian> l=getLibrarians();
        List<Long> l1=new ArrayList<>(l.keySet());
        Random r=new Random();
        Map<Long,Book> m=getBooks();
        Map<Long,Student> students=getStudents();
        for(Map.Entry<Long,Book> book:m.entrySet())
        {
            if(book.getValue().getBookName().equalsIgnoreCase(name))
            {

                Long key= l1.get(r.nextInt(l1.size()));
                Request l2=new Request(book.getValue(),students.get(idi),l.get(key));
                borrowBookRequest(l2);
                System.out.println(l2);
            }
        }
    }

    public void returnProccess(String bookn)
    {
        Map<Long,Loan> loan=getLL();
        Map<Long,Librarian> l4=getLibrarians();
        List<Long> l3=new ArrayList<>(l4.keySet());
        Random rand=new Random();
        for(Loan loan1:loan.values())
        {
            if(loan1.getB().getBookName().equalsIgnoreCase(bookn))
            {
                Long k= l3.get(rand.nextInt(l3.size()));
                loan1.setReceivedBy(l4.get(k));
                if(returnBookRequest(loan1,l4.get(k))) {
                    addToHistory(loan1);
                    loan.remove(loan1.getId());
                    removeFromLoans(loan);
                }
            }
        }
    }
}

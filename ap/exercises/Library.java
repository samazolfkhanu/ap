package ap.exercises;

import java.io.Serializable;
import java.util.*;

public class Library implements Serializable {
    private String name;
    private List<Book> books;
    private List<Student> students;
    private List<Librarian> librarians;
    private List<Loan> loan;
    FileHandling<Book> f = new FileHandling<>();
    FileHandling<Student> f1 = new FileHandling<>();
    FileHandling<Librarian> f2 = new FileHandling<>();
    public Library()
    {}
    public Library(String name) {
        if (name != null) {
            this.name = name;
            books = new ArrayList<>();
            students = new ArrayList<>();
            librarians = new ArrayList<>();
            loan = new ArrayList<>();
        } else
            throw new NullPointerException("Name of library can not be null!");
    }

    public void addBook(Book b) {
        if (books.contains(b)) {
            System.out.println("Book already have been added!");
        } else {
            if (b != null) {
                books.add(b);
                System.out.println("book added successfully!");
                f.writeToFile(b, "F:/JavaProject/ap/exercises/Books.txt");
            } else
                throw new NullPointerException("Field of book can not be null!");
        }
    }

    public void registerStudent(Student s) {
        if (students.contains(s))
            System.out.println("Student already have registered!");
        else {
            if (s != null) {
                students.add(s);
                System.out.println("Student added successfully!");
                f1.writeToFile(s, "F:/JavaProject/ap/exercises/Students.txt");
                System.out.println("Information: \n" +s);
            } else
                throw new NullPointerException("Field of Student can not be Null!");
        }
    }

    public void registerLibrarian(Librarian l) {
        if (librarians.contains(l))
            System.out.println("Librarian already have registered!");
        else {
            if (l != null) {
                librarians.add(l);
                System.out.println("Librarian added successfully");
                f2.writeToFile(l, "F:/JavaProject/ap/exercises/Librarians.txt");
                System.out.println("Information: \n" + l);
            } else
                throw new NullPointerException("Field of Student can not be Null!");
        }
    }

    public boolean borrowBook(Loan l) {
        if (!students.contains(l.getS()))
            return false;
        if (!books.contains(l.getB()))
            return false;

        if (l.getS().getTrust() >= 5) {
            System.out.println("reached to the limit of borrowing book!");
            return false;
        }
        l.getIssuedBy().increasenOBB();
        l.getB().increseBorrowCount();
        loan.add(l);
        l.getS().increaseTrust();
        System.out.println("Book borrowed successfully");
        return true;
    }

    public boolean returnBook(Loan l) {
        if (!students.contains(l.getS()))
            return false;

        if (loan.contains(l)) {
            System.out.println("Book returned successfully");
            l.getS().reduceTrust();
            l.getReceivedBy().increaseORB();
            return true;
        }
        return false;
    }

    public List<Loan> getLL() {
        return loan;
    }

    public List<Librarian> getLibrarians() {
        return librarians = f2.readFromFile("F:/JavaProject/ap/exercises/Librarians.txt");
    }

    public List<Book> getBooks() {
        return books = f.readFromFile("F:/JavaProject/ap/exercises/Books.txt");
    }

    public List<Student> getStudents() {
        return students = f1.readFromFile("F:/JavaProject/ap/exercises/Students.txt");
    }
    public void addNew(List<Librarian> l)
    {
        this.librarians=l;
        f2.clearFile("F:/JavaProject/ap/exercises/Librarians.txt");
        for(Librarian l1:l)
        {
            f2.writeToFile(l1,"F:/JavaProject/ap/exercises/Librarians.txt");
        }
    }
}

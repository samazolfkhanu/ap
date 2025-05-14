package ap.exercises;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Manager implements Serializable
{
    private String name;
    private String familyName;
    private String educationLevel;

    public Manager(String name,String familyName,String educationLevel)
    {
        if(name!=null && familyName!=null && educationLevel!=null)
        {
            this.name=name;
            this.familyName=familyName;
            this.educationLevel=educationLevel;
        }
        else
            throw new NullPointerException("Invalid Input!");
    }

    public List<Book> getborrowdBook(Library lib)
    {
        List<Loan> l=lib.getLL();
        ArrayList<Book> b=new ArrayList<>();
        for(Loan l1:l)
        {
            if(l1.getDelayDays()>0)
                b.add(l1.getB());
        }
        return b;
    }


    public List<Librarian> librarian(Library lib)
    {
        List<Librarian> m=new ArrayList<>();
        return m=lib.getLibrarians();
    }

    public Book[] getTopBooks(Library lib)
    {
        List<Book> a=new ArrayList<>();
        a=lib.getBooks();
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
}
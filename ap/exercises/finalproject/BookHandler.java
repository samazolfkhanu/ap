package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.List;

public class BookHandler
{
    FileHandling<Book> f;
    private List<Book> books;

    public BookHandler()
    {
        books=new ArrayList<>();
        f=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Books.txt");
    }

    public void displayAvailableBooks()
    {
        if(!books.isEmpty())
            books.clear();
        books=f.readFromFile(Book.class);
        if(!books.isEmpty())
        {
            for(Book book:books)
                System.out.println(book);
        }
        else
        {
            System.out.println("No Book!");
        }
    }

    public List<Book> getBooks()
    {
        return books;
    }
}


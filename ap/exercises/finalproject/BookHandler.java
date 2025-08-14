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

    public Book isBookAvailable(String name,String author,int publishedYear)
    {
        if(!books.isEmpty())
            books.clear();
        books=f.readFromFile(Book.class);
        for(Book book:books)
        {
            if(book.getName().equalsIgnoreCase(name) && book.getAuthor().equalsIgnoreCase(author) && book.getPublishedYear()==publishedYear)
                if(book.getState().equalsIgnoreCase("AVAILABLE")) {
                    book.setState1();
                    updateFile(books);
                    return book;
                }
        }
        return null;
    }

    public void updateFile(List<Book> book)
    {
        for(Book b:book)
        {
            f.writeInFile(b);
        }
    }
}


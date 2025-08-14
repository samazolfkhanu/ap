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

    public void getBooks()
    {
        if(!books.isEmpty())
            books.clear();
        books=f.readFromFile(Book.class);
    }
    public void updateFile(List<Book> book)
    {
        for(Book b:book)
        {
            f.writeInFile(b);
        }
    }

    public void displayAvailableBooks()
    {
        getBooks();
        if(!books.isEmpty())
        {
            for(Book book:books)
            {
                if(book.getState().equalsIgnoreCase("AVAILABLE"))
                    System.out.println(book);
            }
        }
        else
        {
            throw new BookException("No Book In List!");
        }
    }

    public Book isBookAvailable(String name,String author,int publishedYear)
    {
        getBooks();
        if(!books.isEmpty())
        {
            for(Book book:books)
            {
                if(book.getName().equalsIgnoreCase(name) &&
                        book.getAuthor().equalsIgnoreCase(author) &&
                        book.getPublishedYear()==publishedYear)

                    if(book.getState().equalsIgnoreCase("AVAILABLE")) {
                        book.setState1();
                        updateFile(books);
                        return book;
                    }
            }
        }
        else
        {
            throw new BookException("No Book In List!");
        }
        System.out.println("Book Not Found!");
        return null;
    }

    public void searchBook(String name,String author,int publishedYear)
    {
        getBooks();
        if(!books.isEmpty())
        {
            boolean isFound=false;
            for(Book book:books)
            {
                if(book.getName().equalsIgnoreCase(name) &&
                        book.getAuthor().equalsIgnoreCase(author) &&
                        book.getPublishedYear()==publishedYear) {
                    isFound=true;
                    System.out.println(book);
                    break;
                }
            }
            if(!isFound)
                System.out.println("Book Not Found!");
        }
        else
        {
            throw new BookException("No Book In List!");
        }
    }
}


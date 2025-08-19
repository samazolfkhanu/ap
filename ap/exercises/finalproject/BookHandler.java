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

    public void addBook(String name,String author,int publishedYear) throws InvalidEntrance {
        getBooks();
        Book b=new Book(name,author,publishedYear);
        if(books.contains(b))
            throw new InvalidEntrance("Book Has Already Added!<500>");
        f.writeInFile(b);
        System.out.println("Book Added Successfully!");
    }
    public void getBooks()
    {
        if(!books.isEmpty())
            books.clear();
        books=f.readFromFile(Book.class);
    }
    public void updateFile(List<Book> book)
    {
        f.clearFile();
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
                        book.setState("reserved");
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

    public Book searchBook(String name,String author,int publishedYear)
    {
        getBooks();
        if(!books.isEmpty())
        {
            for(Book book:books)
            {
                if(book.getName().equalsIgnoreCase(name) &&
                        book.getAuthor().equalsIgnoreCase(author) &&
                        book.getPublishedYear()==publishedYear) {
                    return book;
                }
            }
        }
        else
        {
            throw new BookException("No Book In List!");
        }
        return null;
    }
    public void editBookName(Book book,String name) throws InvalidEntrance {
        getBooks();
        for(Book b:books)
        {
            if(b.getName().equals(book.getName()) &&
                    b.getAuthor().equals(book.getAuthor()) &&
                    b.getPublishedYear()==book.getPublishedYear())
                b.setName(name);

        }
        updateFile(books);
    }
    public void editBookAuthor(Book book,String author) throws InvalidEntrance {
        getBooks();
        for(Book b:books)
        {
            if(b.getName().equals(book.getName()) &&
                    b.getAuthor().equals(book.getAuthor()) &&
                    b.getPublishedYear()==book.getPublishedYear())
                b.setAuthor(author);

        }
        updateFile(books);
    }
    public void editBookYear(Book book,int year) throws InvalidEntrance {
        getBooks();
        for(Book b:books)
        {
            if(b.getName().equals(book.getName()) &&
                    b.getAuthor().equals(book.getAuthor()) &&
                    b.getPublishedYear()==book.getPublishedYear())
                b.setPublishedYear(year);

        }
        updateFile(books);
    }

    public void editBookState(Book book,String state)
    {
        getBooks();
        for(Book b:books)
        {
            if(b.getName().equals(book.getName()) &&
                    b.getAuthor().equals(book.getAuthor()) &&
                    b.getPublishedYear()==book.getPublishedYear())
                b.setState(state);

        }
        updateFile(books);
    }

    public void searchBookByGuest(String name)
    {
        getBooks();
        if(!books.isEmpty())
        {
            boolean isFound=false;
            for(Book book:books)
            {
                if(book.getName().equalsIgnoreCase(name))
                {
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

    public int getBookCount()
    {
        getBooks();
        return books.size();
    }
}


package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.List;

public class BookHandler implements List_Tool<Book>
{
    FileHandling<Book> f;
    private List<Book> books;

    public BookHandler()
    {
        books=new ArrayList<>();
        f=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Books.json");
    }

    public void addBook(String name,String author,int publishedYear) throws InvalidEntrance {
        getList();
        Book b=new Book(name.trim(),author.trim(),publishedYear);
        if(books!=null && books.contains(b))
            throw new InvalidEntrance("Book Has Already Added!<500>");
        f.writeInFile(b, Book.class);
        System.out.println("Book Added Successfully!");
    }
    public void getList()
    {
        if(books!=null && !books.isEmpty())
            books.clear();
        books=f.readFromFile(Book.class);
    }
    public void updateList(List<Book> book)
    {
        f.clearFile();
        for(Book b:book)
        {
            f.writeInFile(b,Book.class);
        }
    }

    public void displayAvailableBooks()
    {
        getList();
        if(!books.isEmpty())
        {
            books.stream()
                    .filter(x->x.getState().equalsIgnoreCase("AVAILABLE"))
                    .forEach(System.out::println);
        }
        else
        {
            throw new BookException("No Book In List!");
        }
    }

    public Book isBookAvailable(String name,String author,int publishedYear)
    {
        getList();
        if(books!=null && !books.isEmpty())
        {
            Book b=books.stream()
                    .filter(x->x.getName().equalsIgnoreCase(name) &&
                            x.getAuthor().equalsIgnoreCase(author) &&
                            x.getPublishedYear()==publishedYear)
                    .findFirst()
                    .get();
            if(b.getState().equalsIgnoreCase("AVAILABLE")) {
                updateList(books);
                return b;
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
        getList();
        if(!books.isEmpty())
        {
            Book b=books.stream()
                    .filter(x->x.getAuthor().equalsIgnoreCase(author) &&
                            x.getName().equalsIgnoreCase(name) &&
                            x.getPublishedYear()==publishedYear)
                    .findFirst()
                    .get();
            return b;
        }
        else
        {
            throw new BookException("No Book In List!");
        }
    }
    public void editBookName(Book book,String name) throws InvalidEntrance {
        getList();
        for(Book b:books)
        {
            if(b.getName().equals(book.getName()) &&
                    b.getAuthor().equals(book.getAuthor()) &&
                    b.getPublishedYear()==book.getPublishedYear())
                b.setName(name);

        }
        updateList(books);
    }
    public void editBookAuthor(Book book,String author) throws InvalidEntrance {
        getList();
        for(Book b:books)
        {
            if(b.getName().equals(book.getName()) &&
                    b.getAuthor().equals(book.getAuthor()) &&
                    b.getPublishedYear()==book.getPublishedYear())
                b.setAuthor(author);

        }
        updateList(books);
    }
    public void editBookYear(Book book,int year) throws InvalidEntrance {
        getList();
        for(Book b:books)
        {
            if(b.getName().equals(book.getName()) &&
                    b.getAuthor().equals(book.getAuthor()) &&
                    b.getPublishedYear()==book.getPublishedYear())
                b.setPublishedYear(year);

        }
        updateList(books);
    }

    public void editBookState(Book book,String state)
    {
        getList();
        for(Book b:books)
        {
            if(b.getName().equals(book.getName()) &&
                    b.getAuthor().equals(book.getAuthor()) &&
                    b.getPublishedYear()==book.getPublishedYear())
                b.setState(state);

        }
        updateList(books);
    }

    public void searchBookByGuest(String name)
    {
        getList();
        if(books!=null && !books.isEmpty())
        {
            Book b=books.stream()
                    .filter(x->x.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .get();
            System.out.println(b);
        }
        else
        {
            throw new BookException("No Book In List!");
        }
    }

    public int getBookCount()
    {
        getList();
        if(books.isEmpty())
            return 0;
        return books.size();
    }
}


package ap.exercises.finalproject;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookHandler implements List_Tool<Book>
{
    private FileHandling<Book> f;
    private Map<String,Book> books;

    public BookHandler()
    {
        books=new HashMap<>();
        f=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Books.json");
    }

    public void addBook(String name,String author,int publishedYear,String ISBN) throws InvalidEntrance {
        getList();
        Book b=new Book(name.trim(),author.trim(),publishedYear,ISBN.trim());
        if(books!=null && books.containsKey(b))
            throw new InvalidEntrance("Book Has Already Added!<500>");
        f.writeInFile(b, Book.class);
        System.out.println("Book Added Successfully!");
    }
    public void getList()
    {
        List<Book> l;
        if(books!=null && !books.isEmpty())
            books.clear();
        l=f.readFromFile(Book.class);
        if(l!=null) {
            books=l.stream()
                    .collect(Collectors.toMap(Book::getISBN, x->x));
        }
    }
    public void updateList(Map<String,Book> book)
    {
        f.clearFile();
        for(Book b:book.values())
        {
            f.writeInFile(b,Book.class);
        }
    }

    public void displayAvailableBooks()
    {
        getList();
        if(!books.isEmpty())
        {
            books.entrySet().stream()
                    .filter(x->x.getValue().getState().equalsIgnoreCase("AVAILABLE"))
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
            Book b=books.entrySet().stream()
                    .filter(x->x.getValue().getName().equalsIgnoreCase(name) &&
                            x.getValue().getAuthor().equalsIgnoreCase(author) &&
                            x.getValue().getPublishedYear()==publishedYear)
                    .findFirst()
                    .get()
                    .getValue();
            if(b.getState().equalsIgnoreCase("AVAILABLE")) {
                updateList(books);
                return b;
            }
        }
        else
        {
            throw new BookException("No Book In List!");
        }
        return null;
    }

    public Book searchBook(String name,String author,int publishedYear)
    {
        getList();
        if(books!=null && !books.isEmpty())
        {
            Book b=books.entrySet().stream()
                    .filter(x->x.getValue().getAuthor().equalsIgnoreCase(author) &&
                            x.getValue().getName().equalsIgnoreCase(name) &&
                            x.getValue().getPublishedYear()==publishedYear)
                    .findFirst()
                    .get()
                    .getValue();
            return b;
        }
        else
        {
            throw new BookException("No Book In List!");
        }
    }
    public void editBookName(Book book,String name) throws InvalidEntrance {
        getList();
        for(Book b:books.values())
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
        for(Book b:books.values())
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
        for(Book b:books.values())
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
        for(Book b:books.values())
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
            Book b=books.entrySet().stream()
                    .filter(x->x.getValue().getName().equalsIgnoreCase(name))
                    .findFirst()
                    .get()
                    .getValue();
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


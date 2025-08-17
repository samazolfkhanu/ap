package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.List;

public class LibrarianManager
{
    private List<Librarian> librarians;
    private FileHandling<Librarian> lF;
    BookHandler bookHandler;

    public LibrarianManager()
    {
        librarians=new ArrayList<>();
        lF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Librarian.txt");
        bookHandler=new BookHandler();
    }

    public Librarian authenticateLibrarian(String username,String password)
    {
        getLibrarians();
        return librarians.stream()
                .filter(l->l.getUsername().equals(username) && l.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void getLibrarians()
    {
        if(!librarians.isEmpty())
            librarians.clear();
        librarians=lF.readFromFile(Librarian.class);
    }

    public void editLibrarianInformation(Librarian librarian,String password) throws InvalidEntrance {
        getLibrarians();
        for(Librarian l:librarians)
        {
            if(librarian.getUsername().equals(l.getUsername()) && librarian.getPassword().equals(l.getPassword()))
            {
                l.setPassword(password);
            }
        }
        updateLibrarian(librarians);
    }

    public void updateLibrarian(List<Librarian> l)
    {
        lF.clearFile();
        for(Librarian librarian:l)
        {
            lF.writeInFile(librarian);
        }
    }
}

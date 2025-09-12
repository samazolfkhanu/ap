package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.List;

public class LibrarianManager implements List_Tool<Librarian>, Account_Handler<Librarian>
{
    private List<Librarian> librarians;
    private FileHandling<Librarian> lF;

    public LibrarianManager()
    {
        librarians=new ArrayList<>();
        lF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Librarians.json");
    }
    public Librarian authenticateUser(String username,String password) {
        getList();
        return librarians.stream()
                .filter(l->l.getUsername().equals(username) && l.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void getList() {
        if(librarians!=null && !librarians.isEmpty())
            librarians.clear();
        librarians=lF.readFromFile(Librarian.class);
    }
    public void editLibrarianInformation(Librarian librarian,String password) throws InvalidEntrance {
        getList();
        for(Librarian l:librarians) {
            if(librarian.getUsername().equals(l.getUsername()) && librarian.getPassword().equals(l.getPassword())) {
                l.setPassword(password);
            }
        }
        updateList(librarians);
    }
    public void increaseLibrarianBook(String username){
        getList();
        for(Librarian l:librarians) {
            if(l.getUsername().equals(username)) {
                l.increaseNumberOfBook();
                break;
            }
        }
        updateList(librarians);
    }
    public void updateList(List<Librarian> l) {
        lF.clearFile();
        for(Librarian librarian:l) {
            lF.writeInFile(librarian,Librarian.class);
        }
    }
    public Librarian getAUser(String username) {
        getList();
        if(librarians!=null)
        {
            return librarians.stream()
                    .filter(x->x.getUsername().equals(username))
                    .findFirst()
                    .get();
        }
        return null;
    }
    public List<Librarian> returnLibrarian() {
        getList();
        return librarians;
    }
    public void librarianHistory(String username)
    {
        getList();
        if(librarians!=null) {
            Librarian l = librarians.stream()
                    .filter(x -> x.getUsername().equals(username))
                    .findFirst()
                    .get();
            System.out.println("Number of Book Registered: " + l.getNumBook());
        }
    }

    public boolean isUsernameTaken(String username)
    {
        getList();
        if(librarians!=null)
            return librarians.stream().anyMatch(s->s.getUsername().equalsIgnoreCase(username));
        return false;
    }
    public void addLibrarian(String username,String id) throws InvalidEntrance {
        Librarian lib=new Librarian(username.trim(),id);
        getList();
        if(librarians!=null && librarians.contains(lib))
            System.out.println("Librarian Has Already Added!");
        if(isUsernameTaken(username))
            System.out.println("This username already exists. Please choose a different username.");
        else
        {
            lF.writeInFile(lib,Librarian.class);
            System.out.println("Librarian Added Successfully!");
        }
    }
}

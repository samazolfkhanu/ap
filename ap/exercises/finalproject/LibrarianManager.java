package ap.exercises.finalproject;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibrarianManager implements List_Tool<Librarian>, Account_Handler<Librarian>
{
    private Map<String,Librarian> librarians;
    private FileHandling<Librarian> lF;

    public LibrarianManager()
    {
        librarians=new HashMap<>();
        lF=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Librarians.json");
    }
    public Librarian authenticateUser(String username,String password) {
        getList();
        return librarians.values().stream()
                .filter(l->l.getUsername().equals(username) && l.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public void getList() {
        List<Librarian> l;
        if(librarians!=null && !librarians.isEmpty())
            librarians.clear();
        l=lF.readFromFile(Librarian.class);
        if(l!=null) {
            librarians=l.stream()
                    .collect(Collectors.toMap(Librarian::getUsername,x->x));
        }
    }
    public void editLibrarianInformation(Librarian librarian,String password) throws InvalidEntrance {
        getList();
        for(Librarian l:librarians.values()) {
            if(librarian.getUsername().equals(l.getUsername()) && librarian.getPassword().equals(l.getPassword())) {
                l.setPassword(password);
            }
        }
        updateList(librarians);
    }
    public void increaseLibrarianBook(String username){
        getList();
        for(Librarian l:librarians.values()) {
            if(l.getUsername().equals(username)) {
                l.increaseNumberOfBook();
                break;
            }
        }
        updateList(librarians);
    }
    public void updateList(Map<String,Librarian> l) {
        lF.clearFile();
        for(Librarian librarian:l.values()) {
            lF.writeInFile(librarian,Librarian.class);
        }
    }
    public Librarian getAUser(String username) {
        getList();
        if(librarians!=null)
        {
            return librarians.entrySet().stream()
                    .filter(x->x.getKey().equals(username))
                    .findFirst()
                    .get()
                    .getValue();
        }
        return null;
    }
    public Map<String,Librarian> returnLibrarian() {
        getList();
        return librarians;
    }
    public void librarianHistory(String username)
    {
        getList();
        if(librarians!=null) {
            Librarian l = librarians.entrySet().stream()
                    .filter(x -> x.getKey().equals(username))
                    .findFirst()
                    .get()
                    .getValue();
            System.out.println("Number of Book Registered: " + l.getNumBook());
        }
    }

    public boolean isUsernameTaken(String username)
    {
        getList();
        if(librarians!=null)
            return librarians.entrySet().stream().anyMatch(s->s.getKey().equalsIgnoreCase(username));
        return false;
    }
    public void addLibrarian(String username,String id) throws InvalidEntrance {
        Librarian lib=new Librarian(username.trim(),id);
        getList();
        if(librarians!=null && librarians.containsKey(lib))
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

package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManagerHandler implements List_Tool<Manager>, Account_Handler<Manager>
{
    private Map<String,Manager> manager;
    private FileHandling<Manager> m;

    public ManagerHandler()
    {
        this.manager=new HashMap<>();
        m=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Manager.json");
    }
    private boolean isUsernameTaken(String username) {
        if(manager!=null)
            return manager.entrySet().stream().anyMatch(s -> s.getKey().equals(username));
        return false;
    }
    public void registerManager(String username,String password) throws InvalidEntrance {
        getList();
        if(isUsernameTaken(username))
        {
            System.out.println("This username already exists. Please choose a different username.");
            return;
        }
        Manager new_manager=new Manager(username,password);
        m.writeInFile(new_manager,Manager.class);
    }

    public void getList()
    {
        List<Manager> l=new ArrayList<>();
        if(!manager.isEmpty())
            manager.clear();
        l=m.readFromFile(Manager.class);
        if(l!=null)
        {
            manager=l.stream()
                    .collect(Collectors.toMap(Manager::getUsername, m->m));
        }
    }

    @Override
    public Manager getAUser(String username)
    {
        getList();
        if(manager!=null)
        {
            return manager.entrySet().stream()
                    .filter(x->x.getKey().equals(username))
                    .findFirst()
                    .get()
                    .getValue();
        }
        return null;
    }

    @Override
    public void updateList(Map<String,Manager> l) {
        m.clearFile();
        for(Manager manger:l.values())
        {
            m.writeInFile(manger,Manager.class);
        }
    }

    public Manager authenticateUser(String username,String password)
    {
        getList();
        if(manager!=null )
        {
            return manager.values().stream()
                    .filter(x->x.getUsername().equalsIgnoreCase(username) && x.getPassword().equals(password))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

}

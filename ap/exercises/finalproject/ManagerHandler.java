package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.List;

public class ManagerHandler
{
    private List<Manager> manager;
    private FileHandling<Manager> m;

    public ManagerHandler()
    {
        this.manager=new ArrayList<>();
        m=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Manager.txt");
    }

    public void getManager()
    {
        if(!manager.isEmpty())
            manager.clear();
        manager=m.readFromFile(Manager.class);
    }

    public Manager authenticateManager(String username,String password)
    {
        getManager();
        if(manager!=null )
        {
            for(Manager manager1:manager)
            {
                if(manager1.getUsername().equals(username) && manager1.getPassword().equals(password))
                    return manager1;
            }
        }
        return null;
    }

    public void add(String u,String p) throws InvalidEntrance {
        m.writeInFile(new Manager(u,p));
    }
}

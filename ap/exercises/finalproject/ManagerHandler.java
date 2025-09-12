package ap.exercises.finalproject;

import java.util.ArrayList;
import java.util.List;

public class ManagerHandler implements List_Tool<Manager>, Account_Handler<Manager>
{
    private List<Manager> manager;
    private FileHandling<Manager> m;

    public ManagerHandler()
    {
        this.manager=new ArrayList<>();
        m=new FileHandling<>("F:/JavaProject/ap/exercises/finalproject/Manager.json");
    }
    private boolean isUsernameTaken(String username) {
        if(manager!=null)
            return manager.stream().anyMatch(s -> s.getUsername().equals(username));
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
        if(!manager.isEmpty())
            manager.clear();
        manager=m.readFromFile(Manager.class);
    }

    @Override
    public Manager getAUser(String username)
    {
        getList();
        if(manager!=null)
        {
            return manager.stream()
                    .filter(x->x.getUsername().equals(username))
                    .findFirst()
                    .get();
        }
        return null;
    }

    @Override
    public void updateList(List<Manager> l) {
        m.clearFile();
        for(Manager manger:l)
        {
            m.writeInFile(manger,Manager.class);
        }
    }

    public Manager authenticateUser(String username,String password)
    {
        getList();
        if(manager!=null )
        {
            return manager.stream()
                    .filter(x->x.getUsername().equalsIgnoreCase(username) && x.getPassword().equals(password))
                    .findFirst()
                    .get();
        }
        return null;
    }

}

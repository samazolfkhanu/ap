package ap.exercises.finalproject;


import java.io.Serializable;

public class Manager extends User implements Serializable
{

    public Manager(String username,String password) throws InvalidEntrance {
        super(username,password);
    }

    public String getUsername()
    {
        return super.getUsername();
    }
    public String getPassword()
    {
        return super.getPassword();
    }

    public String toString()
    {
        return "Manager Info:\n\tUsername: "+getUsername();
    }
}

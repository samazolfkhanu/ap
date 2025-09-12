package ap.exercises.finalproject;

public class Manager extends User
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

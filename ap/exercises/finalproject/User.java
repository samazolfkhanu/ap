package ap.exercises.finalproject;

public class User {
    private String username;
    private String password;

    public User(String username,String password) throws InvalidEntrance {
        if(username!=null && password!=null)
        {
            this.username=username;
            this.password=password;
        }
        else
            throw new InvalidEntrance("Invalid Input!");
    }

    public String getUsername(){
        return username;
    }
    public String getPassword()
    {
        return password;
    }
    public void editPassword(String password)
    {
        this.password=password;
    }
    @Override
    public String toString()
    {
        return "Username: "+username+"\nPassword: "+password;
    }

}

package ap.exercises.finalproject;

import java.io.Serializable;

public class Manager implements Serializable
{
    private String username;
    private String password;

    public Manager(String username,String password) throws InvalidEntrance {
        if(username!=null && password!=null)
        {
            this.username=username;
            this.password=password;
        }
        else
        {
            throw new InvalidEntrance("Invalid Input! <600>");
        }
    }

    public void setPassword(String password) throws InvalidEntrance {
        if(password!=null)
            this.password=password;
        else
            throw new InvalidEntrance("Invalid Password! <601>");
    }
    public void setUsername(String username) throws InvalidEntrance {
        if(username!=null)
            this.username=username;
        else
            throw new InvalidEntrance("Invalid Username! <602>");
    }

    public String getUsername()
    {
        return this.username;
    }
    public String getPassword()
    {
        return this.password;
    }
}

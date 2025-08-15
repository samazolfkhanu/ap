package ap.exercises.finalproject;

import java.io.Serializable;

public class Librarian implements Serializable,User
{
    private String username;
    private String password;

    public Librarian(String username,String password)throws InvalidEntrance
    {
        if(!username.isEmpty() && !password.isEmpty() && !password.contains("!@#$%^&*()_+"))
        {
            this.username=username;
            this.password=password;
        }
        else
            throw new InvalidEntrance("Invalid Inputs! <400>");
    }

    public String getUsername()
    {
        return this.username;
    }
    public String getPassword()
    {
        return this.password;
    }

    public String toString()
    {
        return "Librarian Info:\n" +
                "Username: "+this.username+" | "+"Password: "+this.password;
    }
}

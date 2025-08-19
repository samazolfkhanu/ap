package ap.exercises.finalproject;

import java.io.Serializable;

public class Librarian implements Serializable
{
    private String username;
    private String password;
    private int numBook;
    public Librarian(String username,String password)throws InvalidEntrance
    {
        if(!username.isEmpty() && !password.isEmpty() && !password.contains("!@#$%^&*()_+"))
        {
            this.username=username;
            this.password=password;
            this.numBook=0;
        }
        else
            throw new InvalidEntrance("Invalid Inputs! <400>");
    }

    public void increaseNumberOfBook()
    {
        numBook++;
    }
    public int getNumBook()
    {
        return numBook;
    }
    public String getUsername()
    {
        return this.username;
    }
    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)throws InvalidEntrance
    {
        if(!password.isEmpty() && !password.contains("!@#$%^&*()_+"))
        {
            this.password=password;
        }
        else
            throw new InvalidEntrance("Invalid Password! <401>");
    }


    public String toString()
    {
        return "Librarian Info:\n" +
                "Username: "+this.username+" | "+"Password: "+this.password;
    }
}

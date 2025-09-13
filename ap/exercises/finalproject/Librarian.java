package ap.exercises.finalproject;

import java.io.Serializable;

public class Librarian extends User
{
    private int numBook;
    public Librarian(String username,String password)throws InvalidEntrance
    {
        super(username,password);
        if(!username.isEmpty() && !password.isEmpty() && !password.contains("!@#$%^&*()_+"))
        {
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
        return super.getUsername();
    }
    public String getPassword()
    {
        return super.getPassword();
    }

    public void setPassword(String password)throws InvalidEntrance
    {
        if(!password.isEmpty() && !password.contains("!@#$%^&*()_+"))
        {
            super.editPassword(password);
        }
        else
            throw new InvalidEntrance("Invalid Password! <401>");
    }


    public String toString()
    {
        return "Librarian Info:\n" +
                "\tUsername: "+super.getUsername()+" | "+"Number Of Registered Book: "+numBook;
    }
}

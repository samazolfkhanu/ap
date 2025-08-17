package ap.exercises.finalproject;

import java.io.Serializable;

public class Book implements Serializable
{
    private String name;
    private String author;
    private int publishedYear;
    private BookState state;
    public Book(String name,String author,int publishedYear)throws InvalidEntrance
    {
        if(!name.isEmpty() && !author.isEmpty() && publishedYear>0)
        {
            this.name=name;
            this.author=author;
            this.publishedYear=publishedYear;
            this.state=BookState.AVAILABLE;
        }
        else
        {
            throw new InvalidEntrance("Invalid Inputs! <100>");
        }
    }
    public Book()
    {}
    public String getName()
    {
        return this.name;
    }
    public String getAuthor()
    {
        return this.author;
    }
    public int getPublishedYear()
    {
        return this.publishedYear;
    }
    public String getState()
    {
        return this.state.name();
    }

    public void setState1()
    {
        this.state=BookState.search("reserved");
    }

    @Override
    public String toString()
    {
        return "Book Info:\n" +
                "Name: "+this.name+
                " | Author: "+this.author+
                " | Published Year: "+this.publishedYear+
                " | State: "+this.state.name();
    }

}
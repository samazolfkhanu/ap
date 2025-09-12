package ap.exercises.finalproject;

import java.io.Serializable;

public class Book implements Serializable
{
    private String name;
    private String author;
    private int publishedYear;
    private BookState state;
    private String ISBN;
    public Book(String name,String author,int publishedYear,String ISBN)throws InvalidEntrance
    {
        if(!name.isEmpty() && !author.isEmpty() && publishedYear>0 && ISBN!=null)
        {
            this.name=name;
            this.author=author;
            this.publishedYear=publishedYear;
            this.state=BookState.AVAILABLE;
            this.ISBN=ISBN;
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

    public void setState(String n)
    {
        this.state=BookState.search(n);
    }
    public void setName(String name) throws InvalidEntrance {
        if(!name.isEmpty())
            this.name=name;
        else
            throw new InvalidEntrance("Invalid Name!<101>");
    }
    public String getISBN()
    {
        return ISBN;
    }
    public void setAuthor(String author) throws InvalidEntrance {
        if(!author.isEmpty())
            this.author=author;
        else
            throw new InvalidEntrance("Invalid Author Name!<102>");
    }
    public void setPublishedYear(int year) throws InvalidEntrance {
        if(year>0)
            this.publishedYear=year;
        else
            throw new InvalidEntrance("Invalid Input!<103");
    }

    @Override
    public String toString()
    {
        return "Book Info:\n" +
                "Name: "+this.name+
                " | Author: "+this.author+
                " | Published Year: "+this.publishedYear+
                " | State: "+this.state.name()+
                " | ISBN: "+this.ISBN;
    }

}
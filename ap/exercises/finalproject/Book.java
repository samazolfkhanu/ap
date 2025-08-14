package ap.exercises.finalproject;

public class Book
{
    private String name;
    private String author;
    private int publishedYear;
    private BookState state;
    public Book(String name,String author,int publishedYear,String state)throws InvalidEntrance
    {
        if(!name.isEmpty() && !author.isEmpty() && publishedYear>0 && !state.isEmpty())
        {
            this.name=name;
            this.author=author;
            this.publishedYear=publishedYear;
            this.state=BookState.search(state);
        }
        else
        {
            throw new InvalidEntrance("Invalid Inputs! <100>");
        }
    }
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
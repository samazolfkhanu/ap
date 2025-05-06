package Quiz;

public class Book extends Shop
{
    private String name;

    public Book(double price,String name) throws InvalidInputException
    {
        super(price);
        if(name!=null)
        {
            this.name=name;
        }
        else
            throw new InvalidInputException("Invalid Input");
    }

    @Override
    public String toString()
    {
        return "Book\n"+"Price: "+getPrice()+"\t  Name: "+name;
    }
}

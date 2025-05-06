package Quiz;


public class Shop
{
    private double price;

    public Shop(double price) throws InvalidInputException
    {
        if(price>0)
            this.price=price;
        else
            throw new InvalidInputException("Invalid input!");
    }
    public double getPrice()
    {
        return price;
    }

    @Override
    public String toString()
    {
        return "Price: "+price;
    }
}

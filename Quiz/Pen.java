package Quiz;

public class Pen extends Shop
{
    private String color;
    private String brand;
    public Pen(double price,String color,String brand) throws InvalidInputException
    {
        super(price);
        if(color!=null && brand!=null)
        {
            this.brand=brand;
            this.color=color;
        }
        else
            throw new InvalidInputException("Invalid Input!");
    }

    @Override
    public String toString()
    {
        return "Pen\n"+"Price :"+getPrice()+"\tBrand: "+brand+"Color: "+color;
    }
}

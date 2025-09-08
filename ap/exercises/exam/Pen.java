package ap.exercises.exam;

public class Pen extends Product{
    private PenColor color;

    public Pen(String name,double price,String color)
    {
        super(name,price);
        if(color!=null)
        {
            this.color=PenColor.search(color);
        }
        else
            throw new ProductExeception("Invalid Input!<300>");
    }
    public String getColor()
    {
        return color.name();
    }
    @Override
    public boolean equals(Object o)
    {
        if(o==null)
            return false;
        if(!o.getClass().equals(this.getClass()))
            return false;
        Pen b=(Pen) o;
        return b.getName().equals(this.getName()) && b.getPrice()==this.getPrice() &&  b.getColor().equals(this.getColor());
    }

    @Override
    public int hashCode()
    {
        int z=this.getName().hashCode();
        int c=(int)this.getPrice();
        int x=this.getColor().hashCode();
        return x+c+z;
    }

    @Override
    public String toString()
    {
        return "Product Name: "+super.getName()+"\nPrice: "+super.getPrice()+"\nColor: "+color;
    }
}
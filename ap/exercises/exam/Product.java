package ap.exercises.exam;

public class Product {
    private String name;
    private double price;

    public Product(String name,double price)
    {
        if(name!=null && price>0)
        {
            this.name=name;
            this.price=price;
        }
        else {
            throw new ProductExeception("Invalid Input!<100>");
        }
    }

    public String getName()
    {
        return name;
    }
    public double getPrice()
    {
        return price;
    }
    @Override
    public String toString()
    {
        return "Product Name: "+name+"\nPrice: "+price;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Product)obj).name) && this.price==(((Product)obj).price);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode()+(this.price+"").hashCode();
    }
}

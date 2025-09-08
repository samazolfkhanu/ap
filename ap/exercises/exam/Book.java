package ap.exercises.exam;


public class Book extends Product{
    private String title;
    private String author;

    public Book(String name,double price,String title,String author)
    {
        super(name,price);
        if(title!=null && author!=null)
        {
            this.author=author;
            this.title=title;
        }
        else
            throw new ProductExeception("Invalid Input!<200>");
    }
    public String getTitle()
    {
        return title;
    }

    public String getAuthor()
    {
        return author;
    }
    @Override
    public String toString()
    {
        return "Product Name: "+super.getName()+"\n Title: "+title+"\nAuthor: "+author+"\nPrice: "+super.getPrice();
    }

    @Override
    public boolean equals(Object o)
    {
        if(o==null)
            return false;
        if(!o.getClass().equals(this.getClass()))
            return false;
        Book b=(Book) o;
        return b.getName().equals(this.getName()) && b.getPrice()==this.getPrice() &&  b.getTitle().equals(this.getTitle()) && b.getAuthor().equals(this.getAuthor());
    }

    @Override
    public int hashCode()
    {
        int x=this.author.hashCode();
        int y=this.title.hashCode();
        int z=this.getName().hashCode();
        int c=(int)this.getPrice();
        return x+y+z+c;
    }
}
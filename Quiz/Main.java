package Quiz;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        List<Shop> l=new ArrayList<>();
        try
        {
            Shop p1=new Pen(20,"Black","bik");
            Shop p2=new Pen(30,"yellow","bik");
            Shop b1=new Book(20,"sama");
            Shop b2=new Book(30,"sky");
            l.add(p1);
            l.add(p2);
            l.add(b1);
            l.add(b2);
        }
        catch(InvalidInputException e)
        {
            System.out.println(e.getMessage());
        }

        for(Shop p:l)
        {
            if(p instanceof Book)
            {
                System.out.println(p.toString());
            }
        }
        for(Shop p:l)
        {
            if(p instanceof Pen)
            {
                System.out.println(p.toString());
            }
        }
    }
}

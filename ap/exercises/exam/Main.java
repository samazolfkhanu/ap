package ap.exercises.exam;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products=new ArrayList<>();
        products.add(new Book("Book",30,"Sky","Sama"));
        products.add(new Pen("Pen",25,"blue"));
        products.add(new Book("Book",30,"Cloud","Sima"));
        products.add(new Pen("Pen",50,"green"));
        products.add(new Pen("Pen",50,"green"));
        products.add(new Pen("Pen",50,"green"));
        products.add(new Pen("Pen",50,"green"));
        products.add(new Pen("Pen",50,"green"));


        List<Product> pr=ProductTools.sort(products);
        for(Product o:pr)
            System.out.println(o);
        System.out.println(ProductTools.ch(products));
        List<Product> p=new ArrayList<>();
        for(Product a:p)
        {
            System.out.println(a);
        }
    }
}

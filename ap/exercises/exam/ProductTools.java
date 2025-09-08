package ap.exercises.exam;

import java.util.*;
import java.util.stream.Collectors;

public class ProductTools {
    public static List<Product> sort(List<Product> list)
    {
        Set<Product> set = new HashSet<>(list);
        List<Product> p=set.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();
        List<Product> l=new ArrayList<>();
        HashSet<Product> s1=new HashSet<>();
        for(Product pr:p)
        {
            for(Product s:p)
            {
                if(pr.getPrice()==s.getPrice())
                {
                    if(pr.getClass().equals(Pen.class))
                    {
                        s1.add(s);
                    }
                }
                else
                    s1.add(s);
            }
        }

        l.addAll(s1);
        return l;
    }

    public static Pair<Book,Pen> ch(List<Product> l)
    {
        Product b=l.stream()
                .filter(x->x.getName().equals("Book"))
                .max(Comparator.comparingDouble(Product::getPrice))
                .get();
        Product p=l.stream()
                .filter(x->x.getName().equals("Pen"))
                .max(Comparator.comparingDouble(Product::getPrice))
                .get();
        Pair<Book,Pen> pair=new Pair((Book)b,(Pen)p);
        return pair;
    }

    public static Map<String,Long> pen(List<Pen> p) {
        return p.stream()
                .collect(Collectors.groupingBy(Pen::getColor,Collectors.counting()));
    }

}


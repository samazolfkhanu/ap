package ap.exercises.ex8;

import java.util.*;
import java.util.stream.Collectors;

public class StringCounterr
{
    private ArrayList<Str> counter;

    public StringCounterr()
    {
        counter=new ArrayList<>();
    }

    public void add(String str)
    {
        boolean isFound=false;
        for(Str s:counter)
        {
            if(s.getS().equals(str)) {
                isFound = true;
                s.increaseCount();
                break;
            }
        }
        if(!isFound)
        {
            Str s=new Str(str);
            counter.add(s);
        }
    }

    public List<String> getTop(int k)
    {
        return counter.stream()
                .sorted(Comparator.comparing(Str::getCount).reversed())
                .limit(k)
                .map(Str::toString)
                .collect(Collectors.toList());
    }
}

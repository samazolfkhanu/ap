package ap.exercises.ex5;

public class Str
{
    private String s;
    private int count;

    public Str(String s)
    {
        this.s=s;
        this.count=1;
    }

    public String getS()
    {
        return s;
    }
    public int getCount()
    {
        return count;
    }

    public void increaseCount()
    {
        count++;
    }

    @Override
    public String toString()
    {
        return "String: "+s+"\nNumber Of Reapet: "+count;
    }
}

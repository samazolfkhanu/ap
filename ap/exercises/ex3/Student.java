package ap.exercises.ex3;

public class Student
{
    private String n;
    private String fN;
    private long id;
    private String dis;

    public Student(String n,String fN,long id,String dis)throws InvalidInputException
    {
        if(n!=null && fN!=null && id>0 && dis!=null)
        {
            this.n=n;
            this.fN=fN;
            this.id=id;
            this.dis=dis;
        }
        else
            throw new InvalidInputException("Error: invalid input!");
    }

    public String getName()
    {
        return n;
    }
    public void setName(String n) throws InvalidInputException
    {
        if(n!=null)
            this.n=n;
        else
            throw new InvalidInputException("invalid input\n");
    }

    public String getFamilyName()
    {
        return fN;
    }
    public void setFamilyName(String fN) throws InvalidInputException
    {
        if(fN!=null)
            this.fN=fN;
        else
            throw new InvalidInputException("invalid input\n");
    }

    public long getid()
    {
        return id;
    }
    public void setid(long id) throws InvalidInputException
    {
        if(id>0)
            this.id=id;
        else
            throw new InvalidInputException("invalid input\n");
    }

    public String getDiscpline()
    {
        return dis;
    }
    public void setDiscpline(String dis)throws InvalidInputException
    {
        if(dis!=null)
            this.dis=dis;
        else
            throw new InvalidInputException("invalid input\n");
    }

    @Override
    public String toString()
    {
        return "Student name: "+this.n+"\tfamily name: "+fN+"\tid: "+id+"\tdiscpline: "+dis+"\n";
    }
}

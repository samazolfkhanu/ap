package ap.exercises.ex3;

import java.io.Serializable;

public class Student implements Serializable
{

    
    private String n;
    private String fN;
    private long id;
    private String dis;
    private int numTrust;
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

    public Student()
    {

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

    public void addTrust()
    {
        if(numTrust<3)
        {
            numTrust++;
        }
        else
            System.out.println("maximum number of book!");
    }

    public int getTrust()
    {
        return numTrust;
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

    public int searchST(Student[] s,String name)
    {
        int i=0;
        boolean found=false;
        for(Student S:s)
        {
            if(S.getName().equals(name))
            {
                found=true;
                return i;
            }
            i++;
        }
        if(!found)
            System.out.println("Student with that name not found!");
        return 0;
    }

    @Override
    public String toString()
    {
        return "Student name: "+this.n+"\tfamily name: "+fN+"\tid: "+id+"\tdiscpline: "+dis+"\t"+"number of book which has been borrowed: "+numTrust+"\n";
    }
}

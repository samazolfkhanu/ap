package ap.exercises;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable
{
    private static int idCount=14030;
    private String n;
    private String fN;
    private long id;
    private String dis;
    private int numBB;
    private LocalDate memberShipDate;
    public Student(String n,String fN,String dis,LocalDate memberShipDate)throws InvalidInputException
    {
        if(n!=null && fN!=null  && dis!=null && memberShipDate!=null)
        {
            idCount+=1;
            this.n=n;
            this.fN=fN;
            this.id=idCount;
            this.dis=dis;
            this.numBB=0;
            this.memberShipDate=memberShipDate;
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

    public void increaseTrust()
    {
        if(numBB<5)
        {
            numBB++;
        }
        else
            System.out.println("maximum number of book!");
    }

    public void reduceTrust()
    {
        if(numBB>=1)
            numBB--;
    }

    public int getTrust()
    {
        return numBB;
    }

    public long getid()
    {
        return id;
    }
    public void setId(long id) throws InvalidInputException
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

    public int searchST(Student[] s, String name)
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
        return -1;
    }

    @Override
    public String toString()
    {
        return "\n[\nStudent name: "+this.n+"\nfamily name: "+fN+"\nid: "+id+"\nMemberShipDdate:"+memberShipDate+"\ndiscpline: "+dis+"\n"+"number of book which has been borrowed: "+numBB+"\n]\n";
    }
}
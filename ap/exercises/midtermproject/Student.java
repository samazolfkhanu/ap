package ap.exercises.midtermproject;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable,HashId
{
    private static final long sv=1L;
    private String n;
    private String fN;
    private long id;
    private String dis;
    private int numBB;
    private LocalDate memberShipDate;
    public Student(String n,String fN,String dis,LocalDate memberShipDate,long id)throws InvalidInputException
    {
        if(n!=null && fN!=null && id>0 && dis!=null && memberShipDate!=null)
        {
            this.n=n;
            this.fN=fN;
            this.id=id;
            this.dis=dis;
            this.numBB=0;
            this.memberShipDate=memberShipDate;
        }
        else
            throw new InvalidInputException("Invalid Input!");

    }

    public Student()
    {
        super();

    }
    public void setName(String name)
    {
        if(name!=null)
            this.n=name;
        else
            throw new NullPointerException("Name is Null!");
    }
    public String getName()
    {
        return n;
    }

    public void setFamilyName(String fname)
    {
        if(fname!=null)
            this.fN=fname;
        else
            throw new NullPointerException("Family Name is Null!");
    }
    public String getFamilyName()
    {
        return fN;
    }

    public void setId(Long id)
    {
        if(id>0)
            this.id=id;
        else
            throw new InvalidInputException("value of Id can not be Negative!");
    }
    public Long getId()
    {
        return id;
    }

    public void increaseTrust()
    {
        if(numBB<5)
        {
            numBB++;
        }
        else
            System.err.println("maximum number of book!");
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

    public String getDiscipline()
    {
        return dis;
    }
    public void setDiscipline(String dis)throws InvalidInputException
    {
        if(dis!=null)
            this.dis=dis;
        else
            throw new InvalidInputException("invalid input\n");
    }
    @Override
    public String toString()
    {
        return ">>Student Information:"+"\n"+"\tName: "+this.n+"\n"+"\tFamily Name: "+fN+"\n"+"\tId: "+id+"\n"+"\tMemberShipDdate: "+memberShipDate+"\n"+"\tDiscpline: "+dis+"\n"+"\tNumber Of Book Which Has Been Borrowed: "+numBB+"\n";
    }
}
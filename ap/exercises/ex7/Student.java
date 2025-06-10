package ap.exercises.ex7;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student implements HashId
{
    private String n;
    private String fN;
    private long id;
    private String dis;
    private int numBB;
    private String  memberShipDate;

    public Student(String n,String fN,String dis,String memberShipDat,long id)throws InvalidInputException
    {
        if(n!=null && fN!=null && id>0 && dis!=null && memberShipDat!=null)
        {
            this.n=n;
            this.fN=fN;
            this.id=id;
            this.dis=dis;
            this.numBB=0;
            this.memberShipDate=memberShipDat;
        }
        else
            throw new InvalidInputException("Invalid Input!");

    }

    public Student()
    {
        super();

    }
    public String toTabSeparatedString() {
        return String.join("\t",
                String.valueOf(id),
                n,
                fN,
                dis,
                String.valueOf(numBB),
                String.valueOf(memberShipDate)
        );
    }

    public static Student fromTabSeparated(String line)
    {
        String[] p=line.split("\t");
        String n=p[1];
        String fn=p[2];
        String dis=p[3];
        Long id=Long.parseLong(p[0]);
        int num=Integer.parseInt(p[4]);
        String dateStr = p[5];
        Student s=new Student(n,fn,dis,dateStr,id);
        s.setNumBB(num);
        return s;
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
    public void setNumBB(int i)
    {
        if(i>=0)
            this.numBB=i;
        else
            throw new IllegalArgumentException("Invalid Input!");
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
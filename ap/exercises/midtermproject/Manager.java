package ap.exercises.midtermproject;

import javax.swing.*;
import java.io.Serializable;


public class Manager implements Serializable,HashId
{
    private static final long sv=1L;
    private String n;
    private String fN;
    private Long id;
    private String educationLevel;

    public Manager(String name,String familyName,Long id,String educationLevel)
    {
        if(name!=null && familyName!=null && id>0 && educationLevel!=null)
        {
            this.n=name;
            this.fN=familyName;
            this.id=id;
            this.educationLevel=educationLevel;
        }
        else
            throw new InvalidInputException("Invalid Input!");
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

    public void setEducationLevel(String e)
    {
        if(e!=null)
            this.educationLevel=e;
        else
            throw new NullPointerException("Education Level can not be Null");
    }
    public String getEducationLevel()
    {
        return educationLevel;
    }
    @Override
    public String toString()
    {
        return ">>Manager Information: \n"+"\tName:"+getName()+"\n"+"\tFamily Name: "+getFamilyName()+"\n"+"\tEducation Level: "+educationLevel+"\n";
    }
}
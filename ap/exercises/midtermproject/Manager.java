package ap.exercises.midtermproject;

import java.io.Serializable;


public class Manager implements Serializable,HashId<Manager>
{
    private static final long sv=1L;
    private String name;
    private String familyName;
    private String educationLevel;
    private Long id;

    public Manager(String name,String familyName,Long id,String educationLevel)
    {
        if(name!=null && familyName!=null && educationLevel!=null && id>0)
        {
            this.name=name;
            this.familyName=familyName;
            this.educationLevel=educationLevel;
            this.id=id;
        }
        else
            throw new NullPointerException("Invalid Input!");
    }

    public Long getId()
    {
        return id;
    }

}
package ap.exercises.midtermproject;

public enum Role
{
    MANAGER(1),
    LIBRARIAN(2),
    STUDENT(3),
    EXIT(0);

    private final int code;

    Role(int code) {
        this.code = code;
    }
    public int getCode()
    {
        return code;
    }


    public static Role search(String name)
    {
        for(Role r:values())
        {
            if(r.name().equalsIgnoreCase(name))
                return r;
        }
        return null;
    }
}

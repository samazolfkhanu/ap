package ap.exercises.finalproject;

public enum AccessLevel
{
    ACTIVE,
    INACTIVE;

    public static AccessLevel search(String n)
    {
        for(AccessLevel p:values())
        {
            if(p.name().equalsIgnoreCase(n))
                return p;
        }
        return null;
    }
}

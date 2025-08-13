package ap.exercises.finalproject;

public enum BookState
{
    AVAILABLE,
    BORROWED,
    RESERVED;

    public static BookState search(String state)
    {
        for(BookState s:values())
        {
            if(s.name().equalsIgnoreCase(state))
                return s;
        }
        return null;
    }
}

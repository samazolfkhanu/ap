package ap.exercises.exam;

public enum PenColor {
    GREEN,
    BLUE,
    RED,
    BLACK;
    public static PenColor search(String color)
    {
        for(PenColor p:values())
        {
            if(p.name().equalsIgnoreCase(color))
            {
                return p;
            }
        }
        return null;
    }
}

package ap.exercises.exam;

public class Pair<T extends Book,M extends Pen> {
    private T b;
    private M p;
    public Pair(T b,M p){
        this.p=p;
        this.b=b;
    }

    public String toString()
    {
        return "("+p+"\n"+b+")";
    }
}

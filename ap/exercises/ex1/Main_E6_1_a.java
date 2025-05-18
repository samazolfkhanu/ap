package ap.exercises.ex1;

public class Main_E6_1_a {
    public static void main(String[] args)
    {
        int m=0;
        for(int i=2;i<=100;i+=2)
        {
            m+=i;
        }
        
        System.out.print("sum of even numbers till 100: "+m);
    }
}

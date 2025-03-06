package ap.E6_1a;

public class SumOfEvenNumbers {
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

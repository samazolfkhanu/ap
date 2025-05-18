package ap.exercises.ex1;
import java.util.Scanner;

public class Main_E6_1_e {
    public static void main(String[] args)
    {
        int m=0,x;
        Scanner scan=new Scanner(System.in);
        System.out.print("enter a number:");
        int n=scan.nextInt();
        while(n>0)
        {
            x=n%10;
            
            if(x%2!=0) m=m+x;
            n=n/10;
        }
        System.out.print("sum of odd digits: "+m);
    }
}


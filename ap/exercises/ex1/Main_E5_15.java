package ap.exercises.ex1;

import java.util.Scanner;

public class Main_E5_15 {

    public static void main(String[] args)
    {
        double t=0;
        Scanner scan=new Scanner(System.in);
        System.out.print("enter your salary: ");
        long s=scan.nextLong();
        if(s>500000)
        {
            t=(50000*0.01)+(75000*0.02)+(100000*0.03)+(250000*0.04)+(((s-500000)*0.05));
        }
        else if(s>250000 && s<500000)
        {
            t=(50000*0.01)+(75000*0.02)+(100000*0.03)+(250000*0.04)+((s-250000)*0.05);
        }
        else if(s>100000 && s<250000)
        {
            t=(50000*0.01)+(75000*0.02)+(100000*0.03)+((s-100000)*0.04);
        }
        else if(s>75000 && s<100000)
        {
            t=(50000*0.01)+(75000*0.02)+((s-75000)*0.03);
        }
        else if(s>50000 && s<75000)
        {
            t=(50000*0.01)+((s-50000)*0.02);
        }
        else
        {
            t=s*0.01;
        }
        System.out.printf("your tax is %f $",t);
    }
    static void tax(int s)
    {

    }
}

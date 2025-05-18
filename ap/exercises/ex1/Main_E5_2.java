package ap.exercises.ex1;

import java.util.Scanner;

public class Main_E5_2 {
    
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.print("enter a number:");
        double num=scan.nextDouble();
        signOfNumber(num);
    }
    static void signOfNumber(double num)
    {
        if(num==0) System.out.print("zero!");
        else if(num>0)
        {
            if(Math.abs(num)>1000000) System.out.print("large positive number!");
            else if(Math.abs(num)<1) System.out.print("small positive number!");
            else System.out.print("positive number!");
        }
        else
        {
            if(Math.abs(num)>1000000) System.out.print("large negative number!");
            else if(Math.abs(num)<1) System.out.print("small negative number!");
            else System.out.print("negative number!");
        }
    }
}

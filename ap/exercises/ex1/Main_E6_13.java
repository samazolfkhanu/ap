package ap.exercises.ex1;

import java.util.Scanner;

public class Main_E6_13 {
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.print("enter your number:");
        int n=scan.nextInt();
        convertToBiary(n);
    }
    static void convertToBiary(int n)
    {
        while(n>0)
        {
            System.out.println(n%2);
            n=n/2;
        }
    }
}

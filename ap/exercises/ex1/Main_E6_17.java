package ap.exercises.ex1;

import java.util.Scanner;

public class Main_E6_17 {
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.print("enter the length of side:");
        int s=scan.nextInt();
        pattern(s);
    }
    
    static void pattern(int n)
    {
        for(int i=0;i<n;i++)
        {
            System.out.print("*");
        }
        System.out.print(" ");
        for(int i=0;i<n;i++)
        {
            System.out.print("*");
        }
        System.out.print("\n");
        for(int i=0;i<n-2;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print("*");
            }
            System.out.print(" *");
            for(int k=0;k<n-2;k++)
            {
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.print("\n");
        }

        for(int i=0;i<n;i++)
        {
            System.out.print("*");
        }
        System.out.print(" ");
        for(int i=0;i<n;i++)
        {
            System.out.print("*");
        }
    }
}

package ap.exercises.ex1;

import java.util.Scanner;

public class Main_E6_18 {
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.print("enter the side length of rhombus:");
        int n=scan.nextInt();
        pattern(n);
        scan.close();
    }
    
    static void pattern(int n)
    {
        int nb=n-1,ns=1;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<nb;j++)
            {
                System.out.print(" ");
            }
            for(int k=0;k<ns;k++)
            {
                System.out.print("*");
            }
            System.out.print("\n");
            nb--;
            ns+=2;
        }
        nb=1;
        ns-=4;
        for(int i=0;i<n-1;i++)
        {
            for(int j=0;j<nb;j++)
            {
                System.out.print(" ");
            }
            for(int k=0;k<ns;k++)
            {
                System.out.print("*");
            }
            System.out.print("\n");
            nb++;
            ns-=2;
        }
    }
}


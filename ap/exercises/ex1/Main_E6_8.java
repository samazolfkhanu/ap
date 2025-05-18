package ap.exercises.ex1;

import java.util.Scanner;

public class Main_E6_8 {
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.print("enter your name: ");
        String s=scan.nextLine();
        print(s);
    }
    static void print(String s)
    {
        for(int i=0;i<s.length();i++)
        {
            System.out.println(s.charAt(i));
        }
    }
}
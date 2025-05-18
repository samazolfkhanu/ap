package ap.exercises.ex1;

import java.util.Scanner;

public class Main_E6_9 {
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.print("enter your name: ");
        String s=scan.nextLine();
        reversing(s);
        scan.close();
    }
    
    static void reversing(String s)
    {
        for(int i=(s.length())-1;i>=0;i--)
        {
            System.out.print(s.charAt(i));
        }
    }
}

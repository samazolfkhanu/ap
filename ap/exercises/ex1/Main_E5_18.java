package ap.exercises.ex1;

import java.util.Arrays;
import java.util.Scanner;

public class Main_E5_18 {
    
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.print("enter three strings:");
        String s=scan.nextLine();
        String[] s2=s.split(" ");
        sort(s2);
    }
    static void sort(String[] s2)
    {
        Arrays.sort(s2);
        for(int i=0;i<s2.length;i++)
        {
            System.out.println(s2[i]);
        }
    }
}
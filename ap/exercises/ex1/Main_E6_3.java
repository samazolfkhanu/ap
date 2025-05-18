package ap.exercises.ex1;

import java.util.Scanner;

public class Main_E6_3 {
    
    public static void main(String[] args)
    {
        int c;
        Scanner scan=new Scanner(System.in);
        System.out.print("enter the sentence:");
        String s=scan.nextLine();
        do {
            System.out.print("\nchoose one of the:\n1.show uppercase letters\n2.show every second letters of string\n3.replace all vowels by underscore and rewrite the sentence\n4.number of vowels\n5.position of all vowels in string\n6.exit\n");
            c=scan.nextInt();
            switch(c)
            {
                case 1:
                    isUpper(s);
                    break;
                case 2:
                    secondLetter(s);
                    break;
                case 3:
                    underScore(s);
                    break;
                case 4:
                    numVowel(s);
                    break;
                case 5:
                    posVowel(s);
                    break;
                case 6:
                    System.out.print("exiting...!");

            }
        }while(c!=6);
        scan.close();
    }
    static void isUpper(String s)
    {
        char[] C=s.toCharArray();
        for(int i=0;i<C.length;i++)
        {
            if (Character.isUpperCase(C[i])) System.out.print(C[i]);
        }
    }
    static void underScore(String s)
    {
        String r=s.replaceAll("[aeiouAEUOI]","_");
        System.out.print(r);
    }

    static void secondLetter(String s)
    {
        String[] s1=s.split(" ");
        for(String S: s1)
        {
            char[] S1=S.toCharArray();
            System.out.print(S1[1]);
        }
    }
    static void posVowel(String s)
    {
        for(int i=0;i<s.length();i++)
        {
            if("aeiouAEIOU".indexOf(s.charAt(i))!=-1)
            {
                System.out.print(i+" ");
            }
        }
    }
    static void numVowel(String s)
    {
        int m=0;
        for(int i=0;i<s.length();i++)
        {
            if("aeiouAEIOU".indexOf(s.charAt(i))!=-1) m++;
        }
        System.out.print("number of vowels:"+m);
    }
}

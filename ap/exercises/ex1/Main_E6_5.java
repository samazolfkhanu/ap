package ap.exercises.ex1;

import java.util.Scanner;

public class Main_E6_5 {
    public static void main(String[] args)
    {
        Scanner scan=new Scanner(System.in);
        System.out.print("enter length of array:");
        int n=scan.nextInt(),c;
        float[] a=new float[n];
        for(int i=0;i<n;i++)
        {
            System.out.printf("enter a[%d]:",i);
            a[i]=scan.nextFloat();
        }
        do {
            System.out.print("\nchoose one of them:\n1.smallest value\n2.largest value\n3.avarage of elements\n4.range of elements\n5.exit\n");
            c=scan.nextInt();
            switch(c)
            {
                case 1:
                    System.out.print("smallest value:"+getSmallest(a,n));
                    break;
                case 2:
                    System.out.print("\nlargest value:"+getLargest(a,n));
                    break;
                case 3:
                    System.out.print("\naverage of array:"+getAverage(a,n));
                    break;
                case 4:
                    System.out.print("\nrange of array:"+getRange(a,n));
                    break;
                case 5:
                    System.out.print("exiting...!");
            }
        }while(c!=5);
    }
    static float getSmallest(float[] a,int n)
    {
        float s=a[0];
        for(int i=0;i<n;i++)
        {
            if(a[i]<s) s=a[i];
        }
        return s;
    }
    static float getLargest(float[] a,int n)
    {
        float l=a[0];
        for(int i=0;i<n;i++)
        {
            if(a[i]>l) l=a[i];
        }
        return l;
    }
    static float getAverage(float[] a,int n)
    {
        float m=0;
        for(int i=0;i<n;i++)
        {
            m+=a[i];
        }
        return (float)m/(float)n;
    }
    static float getRange(float[] a,int n)
    {
        float l=getLargest(a,n);
        float s=getSmallest(a,n);
        return l-s;
    }
}

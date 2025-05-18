package ap.exercises.ex1;

import java.util.Scanner;

public class Main_E6_2 {
    public static void main(String[] args)
    {
        int e=0,o=0,c;
        Scanner scan=new Scanner(System.in);
        System.out.print("enter length of array: ");
        int n=scan.nextInt();
        int[] a=new int[n];
        for(int i=0 ;i<n;i++)
        {
            System.out.printf("enter value of a[%d] ",i);
            a[i]=scan.nextInt();
        }
        do {
            System.out.print("\nchoose one of them:\n1.maximum and minimum of array\n2.number of odd and even numbers in array\n3.cumulative total of elements\n4.adjacent duplicate\n5.exit\n");
            c=scan.nextInt();
            switch(c)
            {
                case 1:
                    isMax(a,n);
                    break;
                case 2:
                    numofEvenOdd(a,n);
                    break;
                case 3:
                    cumulativeTotal(a,n);
                    break;
                case 4:
                    adjacenduplicate(a,n);
                    break;
                case 5:
                    System.out.print("exit the code ...!");
                    break;
            }
        }while(c!=5);
    }
    static void isMax(int[] a,int n)
    {
        //a
        int max=a[0];
        for(int i=0;i<n;i++)
        {
            if(a[i]>max) max=a[i];
        }
        int min=a[0];
        for(int i=0;i<n;i++)
        {
            if(a[i]<min) min=a[i];
        }
        System.out.printf("maximum element: %d\tminimum element:%d",max,min);
    }

    static void numofEvenOdd(int[] a,int n)
    {
        int e=0,o=0;
        for(int i=0;i<n;i++)
        {
            if(a[i]%2==0) e++;
            else o++;
        }
        System.out.printf("\nsum of even numbers:%d\tsum of odd numbers:%d",e,o);
    }

    static void cumulativeTotal(int[] a,int n) {
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                s[i] += a[j];
            }
        }
        System.out.print("\nthe cumulative totals:");
        for (int i = 0; i < n; i++) {
            System.out.print(s[i] + " ");
        }
    }
    static void adjacenduplicate(int[] a,int n)
    {
        System.out.print("\nadjacent duplicate:");
        boolean t=true;
        for(int i=0;i<n-1;i++)
        {
            if (a[i]==a[i+1])
            {
                if(t) System.out.print(a[i]);
                t=false;
            }
            else t=true;
        }
    }
}


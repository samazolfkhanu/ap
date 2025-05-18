package ap.exercises.ex2;

import java.util.Scanner;

public class Main_EX2_PM_1_1
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.print("enter the side of board:");
        int k = s.nextInt();
        for (int j = 0; j < k+2; j++) {
            System.out.print("*");
        }
        System.out.print("\n");
        for (int x = 0; x < k; x++) {
            System.out.print("*");
            for (int y = 0; y < k; y++) {
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.print("\n");
        }
        for (int j = 0; j < k+2; j++)
            System.out.print("*");
    }
}

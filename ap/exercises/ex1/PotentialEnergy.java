import java.util.Scanner;

public class PotentialEnergy {
    public static void main(String[] args)
    {
        final float G=9.8f;
        Scanner scan=new Scanner(System.in);
        System.out.println("enter Mass :");
        double m=scan.nextDouble();
        System.out.println("enter Height :");
        double H=scan.nextDouble();
        double U=m*G*H;
        System.out.printf("Potential Energy ="+"%.2f",U);
    }
}

package ap.exercises.ex4;

import java.util.Scanner;

public class Main_EX4_E3_4
{
    public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        HallwayLightCircuit h=new HallwayLightCircuit();
        String str;
        System.out.println("enter firstSwitch:");
        str=s.nextLine();
        h.setF(str.toLowerCase());
        System.out.println("enter secondSwitch:");
        str=s.nextLine();
        h.setS(str.toLowerCase());
        System.out.println("lamp state: "+h.getLampS());
    }
}

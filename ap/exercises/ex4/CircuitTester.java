package ap.exercises.ex4;

import java.util.Scanner;

public class CircuitTester
{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        HallwayLightCircuit h = new HallwayLightCircuit();
        int c;
        do {
            System.out.println("1.first switch\n2.second switch\n3.exit");
            c = s.nextInt();
            switch (c) {
                case 1:
                    h.toggleSwitch(1);
                    System.out.println("first switch state: " + ((h.getSwitchState(1)==1)?"up":"down"));
                    System.out.println("Lamp State: " +((h.getLampS()==1)?"on":"off") + "\n");
                    break;

                case 2:
                    h.toggleSwitch(2);
                    System.out.println("second switch state: " +((h.getSwitchState(2)==1)?"up":"down"));
                    System.out.println("Lamp State: " +((h.getLampS()==1)?"on":"off")+ "\n");
                    break;

                case 3:
                    System.out.println("exiting the code!");
                    break;
            }
        } while (c != 3);
    }
}

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
                    h.toggleFirstSwitch();
                    System.out.println("Lamp State: " + h.getLampS() + "\n");
                    break;

                case 2:
                    h.toggleSecondSwitch();
                    System.out.println("Lamp State: " + h.getLampS() + "\n");
                    break;

                case 3:
                    System.out.println("exiting the code!");
                    break;
            }
            System.out.println("first switch state: " + ((h.getFirstSwitchState()==1)?"up":"down"));
            System.out.println("second switch state: " +((h.getSecondSwitchState()==1)?"up":"down"));
        } while (c != 3);
    }
}

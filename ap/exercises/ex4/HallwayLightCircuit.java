package ap.exercises.ex4;

public class HallwayLightCircuit
{
    private int firstSwitchState=0;
    private int secondSwitchState=0;
    private int lampS;

    public int getLampS()
    {
        return firstSwitchState ^ secondSwitchState;
    }

    public int getSwitchState(int switchNum)
    {
        switch(switchNum)
        {
            case 1:
                return firstSwitchState;
            case 2:
                return secondSwitchState;
        }
        return 0;
    }
    public void toggleSwitch(int switchNum)
    {
        switch(switchNum)
        {
            case 1:
                firstSwitchState=1-firstSwitchState;
                break;

            case 2:
                secondSwitchState=1-secondSwitchState;
                break;
        }
    }
}

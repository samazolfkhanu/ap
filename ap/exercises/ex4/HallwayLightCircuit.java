package ap.exercises.ex4;

public class HallwayLightCircuit
{
    private int firstSwitchState=0;
    private int secondSwitchState=0;
    private int lampS;

    private void updateLampS()
    {
        lampS=(firstSwitchState==secondSwitchState)?0:1;
    }
    public String getLampS()
    {
        if(lampS==1)
            return "ON";
        return "OFF";
    }

    public int getFirstSwitchState()
    {
        return firstSwitchState;
    }

    public int getSecondSwitchState()
    {
        return secondSwitchState;
    }
    public void toggleFirstSwitch()
    {
        firstSwitchState=1-firstSwitchState;
        updateLampS();
    }
    public void toggleSecondSwitch()
    {
        secondSwitchState=1-secondSwitchState;
        updateLampS();
    }
}

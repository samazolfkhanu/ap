package ap.exercises.ex4;

public class HallwayLightCircuit
{
    private int f;
    private int s;
    private int lampS;

    private void updateLampS()
    {
        lampS=(f==s)?0:1;
    }
    public String getLampS()
    {
        if(lampS==1)
            return "ON";
        return "OFF";
    }

    public int getF()
    {
        return f;
    }

    public int getS()
    {
        return s;
    }
    public void setF(String s)
    {
        switch(s)
        {
            case "up":
                f=1;
                break;
            case "down":
                f=0;
                break;
        }
        updateLampS();
    }
    public void setS(String str)
    {
        switch(str)
        {
            case "up":
                s=1;
                break;
            case "down":
                s=0;
                break;
        }
        updateLampS();
    }
}

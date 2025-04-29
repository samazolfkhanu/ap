package ap.exercises.ex4;

public class Letter
{
    private String from;
    private String to;
    private String body="";

    public Letter(String from,String to)throws InvalidInputException
    {
        if(from!=null && to!=null) {
            this.from = from;
            this.to = to;
            body=body.concat("Dear "+to+":"+"\n"+"\n");
        }
        else
            throw new InvalidInputException("Invalid Input");

    }

    public void addLine(String line)
    {
        if(line==null)
            throw new NullPointerException("Line can not be null!");
        body=body.concat(line).concat("\n");
    }

    public String getText()
    {
        body=body.concat("\n"+"Sincerely,"+"\n"+"\n"+from);
        return body;
    }
}

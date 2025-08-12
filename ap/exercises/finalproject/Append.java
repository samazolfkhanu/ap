package ap.exercises.finalproject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Append  extends ObjectOutputStream
{
    public Append(OutputStream out)throws IOException
    {
        super(out);
    }

    @Override
    protected void writeStreamHeader()throws IOException
    {
        reset();
    }
}

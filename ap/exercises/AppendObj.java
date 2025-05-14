package ap.exercises;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppendObj extends ObjectOutputStream
{
    public AppendObj(OutputStream out)throws IOException
    {
        super(out);
    }
    @Override
    public void writeStreamHeader()throws IOException
    {
        reset();
    }
}
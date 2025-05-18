package ap.exercises.ex3;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

class AppendObj extends ObjectOutputStream
{
    public AppendObj(OutputStream out) throws IOException
    {
        super(out);
    }
    @Override
    public void writeStreamHeader() throws IOException
    {
        reset();
    }
}

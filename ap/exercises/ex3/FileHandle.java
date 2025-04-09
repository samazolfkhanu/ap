package ap.exercises.ex3;

import java.io.*;

class FileHandle
{
    public void writeInFile(Object o,String filePath)
    {
        try
        {
            File f=new File(filePath);
            ObjectOutputStream O;
            if(f.length()==0)
            {
                O=new ObjectOutputStream(new FileOutputStream(filePath));
                O.writeObject(o);
            }
            else
            {
                O=new AppendObj(new FileOutputStream(filePath,true));
                O.writeObject(o);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void readFileB(String filePath)
    {
        try(ObjectInputStream i=new ObjectInputStream(new FileInputStream(filePath)))
        {
            while(true)
            {
                Object obj=i.readObject();
                System.out.println(obj.toString());
            }
        }
        catch(Exception e)
        {
            System.out.println("end of file!");
        }
    }
}


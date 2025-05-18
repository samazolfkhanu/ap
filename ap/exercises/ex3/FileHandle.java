package ap.exercises.ex3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
    public void printAll(String filePath)
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

    @SuppressWarnings("unchcked")
    public <T> List<T> readObjFromFile(String fP, Class<T> clazz)
    {
        List<T> a=new ArrayList<>();
        try(ObjectInputStream o=new ObjectInputStream(new FileInputStream(fP)))
        {
            while(true)
            {
                Object obj= o.readObject();
                if(clazz.isInstance(obj))
                {
                    a.add((T) obj);
                }
            }
        }
        catch(EOFException e)
        {
            System.out.println("end of file!");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return a;
    }

    public void clearFile(String path)
    {
        try
        {
            PrintWriter p=new PrintWriter(path);
            p.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
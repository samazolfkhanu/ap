package ap.exercises;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandling<T>
{
    public void writeToFile(T item,String filePath)
    {
        File f=new File(filePath);
        try
        {
            FileOutputStream out=new FileOutputStream(filePath,true);
            ObjectOutputStream obj;
            if(f.length()!=0)
            {
                obj=new AppendObj(out);
                obj.writeObject(item);
            }
            else {
                obj = new ObjectOutputStream(out);
                obj.writeObject(item);
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
    public List<T> readFromFile(String filePath)
    {
        List<T> l=new ArrayList<>();
        try
        {
            FileInputStream out=new FileInputStream(filePath);
            ObjectInputStream oi=new ObjectInputStream(out);
            try
            {
                while(true)
                {
                    T obj= (T) oi.readObject();
                    l.add(obj);

                }
            }
            catch(ClassNotFoundException er)
            {
                System.out.println(er.getMessage());
            }
            catch(EOFException e)
            {
                System.out.println(" ");
            }
        }
        catch(IOException err)
        {
            System.out.println(err.getMessage());
        }
        return l;
    }

    public void clearFile(String filePath)
    {
        try
        {
            PrintWriter p=new PrintWriter(filePath);
            p.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
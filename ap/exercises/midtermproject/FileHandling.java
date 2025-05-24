package ap.exercises.midtermproject;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class FileHandling<E extends HashId>
{
    public void writeToFile(E item,String filePath)
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
            obj.flush();
            obj.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

    }
    public Map<Long,E> readFromFile(String filePath)
    {
        Map<Long,E> l=new TreeMap<>();
        File f=new File(filePath);
        if(f.length()!=0)
        {
            try
            {
                FileInputStream out=new FileInputStream(filePath);
                ObjectInputStream oi=new ObjectInputStream(out);
                try
                {
                    while(true)
                    {
                        E obj= (E) oi.readObject();
                        l.put(obj.getId(),obj);

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
                oi.close();
            }
            catch(IOException err)
            {
                System.out.println(err.getMessage());
            }
            return l;
        } return null;
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
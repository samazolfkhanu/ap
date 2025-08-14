package ap.exercises.finalproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandling<T>
{
    private String path;
    public FileHandling(String path)
    {
        if(path!=null && !path.isEmpty())
            this.path=path;
        else
        {
            throw new NullPointerException("Path Cannot Be Null!");
        }
    }

    public void writeInFile(T obj)
    {
        File f=new File(path);
        try
        {
            FileOutputStream out=new FileOutputStream(f,true);
            ObjectOutputStream oos;
            if(f.length()!=0)
            {
                oos=new Append(out);
                oos.writeObject(obj);
            }
            else
            {
                oos=new ObjectOutputStream(out);
                oos.writeObject(obj);
            }
            oos.flush();
            oos.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<T> readFromFile(Class<T> clazz)
    {
        ArrayList<T> objectList=new ArrayList<>();
        File f=new File(path);
        if(f.length()!=0)
        {
            try
            {
                ObjectInputStream ois=new ObjectInputStream(new FileInputStream(path));
                try
                {
                    while(true)
                    {
                        T obj=clazz.cast(ois.readObject());
                        objectList.add(obj);
                    }
                }
                catch(ClassNotFoundException cE) {
                    System.out.println(cE.getMessage());
                }
            }catch(IOException iE)
            {
                System.out.println(iE.getMessage());
            }
            return objectList;
        }
        return null;
    }

    public void clearFile()
    {
        try
        {
            PrintWriter p=new PrintWriter(path);
            p.close();
        }catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

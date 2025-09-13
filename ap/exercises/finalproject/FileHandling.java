package ap.exercises.finalproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandling<T>
{
    private Gson g;
    private String path;
    public FileHandling(String path)
    {
        g=new GsonBuilder().registerTypeAdapter(LocalDate.class,new LocalDateAdapter()).setPrettyPrinting().create();
        if(path!=null && !path.isEmpty())
            this.path=path;
        else
        {
            throw new NullPointerException("Path Cannot Be Null!");
        }
    }

    public void writeInFile(T obj,Class<T> clazz)
    {
        List<T> l=readFromFile(clazz);
        try {
            if(l!=null) {
                if(!l.contains(obj)) {
                    FileWriter w=new FileWriter(path);
                    l.add(obj);
                    g.toJson(l,w);
                    w.close();
                }
            }
            else{
                List<T> list=new ArrayList<>();
                list.add(obj);
                FileWriter w=new FileWriter(path,true);
                g.toJson(list,w);
                w.close();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<T> readFromFile(Class<T> clazz) {
        try (FileReader r = new FileReader(path)){
            Type t= TypeToken.getParameterized(List.class,clazz).getType();
            return g.fromJson(r,t);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void clearFile()
    {
        try (FileWriter f=new FileWriter(path,false))
        {
            f.write("");
        }catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

package ap.exercises.ex6;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class HtmlFileManager {

    private String saveFileBasePath;
    private static int saveCounter=0;

    public HtmlFileManager(String saveFileBasePath) {
//        this.saveFileBasePath = DirectoryTools.createDirectoryWithTimeStamp(saveFileBasePath);

        this.saveFileBasePath = saveFileBasePath;
        DirectoryTools.createDirectory(saveFileBasePath);
    }

    public void save(List<String> lines) {
        try {
            String saveHtmlFileAddress = getSaveHtmlFileAddress();
            PrintWriter out = new PrintWriter(saveHtmlFileAddress);
            for (String line : lines) {
                out.println(line);
            }
            out.close();

            System.out.println("save counter: " + saveCounter);
        }catch (Exception e){
            System.out.println("save failed: " + e.getMessage());
        }
    }

    public String getSaveHtmlFileAddress(){
        saveCounter++;
        return saveFileBasePath +"/"+ saveCounter+".html";
    }

    public void writeToFile(Map<String,List<String>> l)
    {
        int c=1;
        try
        {
            for(Map.Entry<String,List<String>> s:l.entrySet())
            {
                try
                {
                    Thread.sleep(3000);
                    File f=new File("F:/JavaProject/ap/exercises/fetched_html/"+s.getKey()+"_/");
                    if(!f.exists())
                    {
                        Path p= Paths.get("F:/JavaProject/ap/exercises/fetched_html/"+s.getKey()+"_/");
                        Files.createDirectories(p);
                        for(String d:s.getValue())
                        {
                            URL u=new URL(d);
                            String[] pa=u.getPath().split("/");
                            if(pa.length!=0)
                            {
                                Path pat= Paths.get("F:/JavaProject/ap/exercises/fetched_html/"+s.getKey()+"_/"+pa[0]);
                                Files.createDirectories(pat);
                                PrintWriter pr=new PrintWriter("F:/JavaProject/ap/exercises/fetched_html/"+s.getKey()+"/"+pa[0]+"_/"+c+".html");
                                List<String> line=HtmlFetcherr.fetchHtml(d);
                                if(!line.isEmpty() && line!=null)
                                {
                                    for(String h:line)
                                    {
                                        pr.println(h);
                                    }
                                }
                                pr.close();
                                c++;
                            }
                            else
                            {
                                PrintWriter pr=new PrintWriter("F:/JavaProject/ap/exercises/fetched_html/"+s.getKey()+"_/"+c+".html");
                                List<String> line=HtmlFetcherr.fetchHtml(d);
                                if(!line.isEmpty())
                                {
                                    for(String h:line)
                                    {
                                        pr.println(h);
                                    }
                                }
                                pr.close();
                                c++;
                            }
                        }
                    }
                    else {
                        for (String d : s.getValue()) {
                            URL u = new URL(d);
                            String[] pa = u.getPath().split("/");
                            if (pa != null && pa.length!=0) {
                                Path pat = Paths.get("F:/JavaProject/ap/exercises/fetched_html/" + s.getKey() + "_/" + pa[0]);
                                Files.createDirectories(pat);
                                PrintWriter pr = new PrintWriter("F:/JavaProject/ap/exercises/fetched_html/" + s.getKey() + "_/" + pa[0] + "/" + c + ".html");
                                List<String> line = HtmlFetcherr.fetchHtml(d);
                                if (!line.isEmpty() && line!=null) {
                                    for (String h : line) {
                                        pr.println(h);
                                    }
                                }
                                pr.close();
                                c++;
                            } else {
                                PrintWriter pr = new PrintWriter("F:/JavaProject/ap/exercises/fetched_html/" + s.getKey() + "_/" + c + ".html");
                                List<String> line = HtmlFetcherr.fetchHtml(d);
                                if (!line.isEmpty()) {
                                    for (String h : line) {
                                        pr.println(h);
                                    }
                                }
                                pr.close();
                                c++;
                            }
                        }
                    }
                }catch(InterruptedException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void saveAudios(List<String> l)
    {
        try {
            int c=1;
            for(String s:l)
            {
                PrintWriter p=new PrintWriter("F:/JavaProject/ap/exercises/Audio"+"/audio"+c+".html");
                p.println(s);
                p.close();
                c++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void saveImages(List<String> l)
    {
        try {
            int c=1;
            for(String s:l)
            {
                PrintWriter p=new PrintWriter("F:/JavaProject/ap/exercises/Image"+"/image"+c+".html");
                p.println(s);
                p.close();
                c++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}

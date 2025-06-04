package ap.exercises.ex6;


import ap.exercises.ex5.Conf;
import ap.exercises.ex5.DirectoryTools;
import ap.exercises.ex5.StringCounterr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class HtmlAnalyzerr {
    private static List<String> fileList = DirectoryTools.getFilesAbsolutePathInDirectory(Conf.SAVE_DIRECTORY);

    public static List<String> getAllUrls() {
        List<String> urls = fileList.stream()
                .map(fileAddress -> FileTool.getTextFileLines(fileAddress))
                .filter(s -> s != null)
                .flatMap(s -> s.stream())
                .map(s -> HtmlParser.getFirstUrl(s))
                .filter(s -> s != null)
                .filter(s -> s.length() > 1)
                .collect(Collectors.toList());
        return urls;
    }

    public static List<String> getTopUrls(int k){
        Map<String, Long> urlCount = getAllUrls().stream()
                .collect(Collectors.groupingBy(
                        s -> s,
                        Collectors.counting()
                ));

        List<String> topUrls = urlCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(k)
                .map(s -> s.getKey())
                .collect(Collectors.toList());

        return topUrls;
    }
    public static void printTopCountUrls(int k){
        StringCounterr urlCounter=new StringCounterr();
        getAllUrls().forEach(urlCounter::add);
        for (String urlCount : urlCounter.getTop(k)) {
            System.out.println(urlCount);
        }
    }

    public static List<String> getAllImageUrls()
    {
        return fileList.stream()
                .map(FileTool::getTextFileLines).filter(Objects::nonNull)
                .flatMap(x->HtmlParser.getAllImageUrls(x,"https://www.znu.ac.ir/").stream())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static void saveAllImageUrlsInFile()
    {
        try
        {
            PrintWriter p=new PrintWriter("F:/JavaProject/ap/exercises/ImageLink.txt");
            for(String s:getAllImageUrls())
            {
                if(s!=null)
                    p.println(s);
            }
            p.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        HtmlAnalyzerr.printTopCountUrls(10);

        System.out.println("____________________");
        HtmlAnalyzerr.getTopUrls(10).forEach(s -> System.out.println(s));
        System.out.println("________________________image URLS____________________________");
        saveAllImageUrlsInFile();
        for(String s:getAllImageUrls())
        {
            System.out.println(s);
        }

    }
}

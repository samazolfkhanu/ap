package ap.exercises.ex5;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HtmlAnalyzer {
    private static List<String> fileList = DirectoryTools.getFilesAbsolutePathInDirectory(Conf.SAVE_DIRECTORY);

    public static List<String> getAllUrls() {
        List<String> urls = fileList.stream()
                .map(fileAddress -> FileTools.getTextFileLines(fileAddress))
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
        StringCounter urlCounter=new StringCounter();
        getAllUrls().forEach(urlCounter::add);
        for (String urlCount : urlCounter.getTop(k)) {
            System.out.println(urlCount);
        }
    }

    public static List<String> getAllImageUrls()
    {
        return fileList.stream()
                .map(FileTools::getTextFileLines)
                .flatMap(x->HtmlParser.getAllImageUrls(x).stream())
                .collect(Collectors.toList());
    }

    public static void saveAllImageUrlsInFile()
    {
        try
        {
            PrintWriter p=new PrintWriter("F:/JavaProject/ap/exercises/ImageLink.txt");
            for(String s:getAllImageUrls())
            {
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

        HtmlAnalyzer.printTopCountUrls(10);

        System.out.println("____________________");
        HtmlAnalyzer.getTopUrls(10).forEach(s -> System.out.println(s));
        System.out.println("________________________image URLS____________________________");
        saveAllImageUrlsInFile();
        for(String s:getAllImageUrls())
        {
            System.out.println(s);
        }

    }
}

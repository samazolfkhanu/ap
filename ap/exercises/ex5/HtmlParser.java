package ap.exercises.ex5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HtmlParser {

    public static String getFirstUrl(String htmlLine) {
        String url = null;
        int startIndex = htmlLine.indexOf("href=\"");
        if (startIndex >= 0) {
            try {
                int hrefLength = "href\"".length();
                int endIndex = htmlLine.indexOf("\"", startIndex + hrefLength + 1);
                url = htmlLine.substring(startIndex + hrefLength + 1, endIndex);
            } catch (Exception e) {
            }
        }
        return url;
    }

    private static List<String> getAllUrlsFromHtmlLinesStream(Stream<String> htmlLinesStream) throws IOException {
        List<String> urls = htmlLinesStream
                .map(line -> getFirstUrl(line))
                .filter(s -> s != null)
                .collect(Collectors.toList());
        return urls;
    }

    public static List<String> getAllUrlsFromFile(String filePath) throws IOException {
        return getAllUrlsFromHtmlLinesStream(Files.lines(Path.of(filePath)));
    }

    public static List<String> getAllUrlsFromList(List<String> htmlLines) throws IOException {
        return getAllUrlsFromHtmlLinesStream(htmlLines.stream());
    }

    public static Set<String> getAllImageUrls(List<String> htmlline)
    {
        Set<String> imagelinks=htmlline.stream()
                .map(z->
                {
                    if(z.contains("src="))
                    {
                        int start=z.indexOf("src=");
                        int beginLink=start+5;
                        char c=z.charAt(4);
                        int end=z.indexOf(c,beginLink);
                        if(end!=-1)
                        {
                           String line=z.substring(beginLink,end);
                           if(line.contains(".jpg") || line.contains(".png") || line.contains("gif") || line.contains(".jpeg"))
                           {
                               return line;
                           }
                        }
                    }
                    return null;
                })
                .collect(Collectors.toSet());
        return imagelinks;
    }
}

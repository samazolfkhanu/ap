package ap.exercises.ex6;


import ap.exercises.ex5.HtmlFetcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DomainHtmlScraper {
    private String domainAddress;
    private Queue<String> queue;

    private HtmlFileManager htmlFileManager;

    public DomainHtmlScraper(String domainAddress, String savePath) {
        this.domainAddress = domainAddress;
        this.queue = new LinkedList<>();
        this.htmlFileManager = new HtmlFileManager(savePath);
    }

    public void start(String domain) throws IOException {
        List<String> htmlLines = HtmlFetcherr.fetchHtml(domainAddress);

        List<String> urls = HtmlParser.getAllUrlsFromList(htmlLines);
        urls.forEach(x -> {
            if (x.contains(domain) && x != null && !x.isEmpty()) {
                queue.add(x);
                System.out.println(x);
            }
        });
        int counter = 1;
        Map<String, List<String>> m = new HashMap<>();
        while (!queue.isEmpty()) {
            String url = queue.remove();
            counter++;
            try {
                URL ur = new URL(url);
                String[] s = ur.getHost().split("\\.");
                if (s.length >= 3) {
                    String subdomain = s[0];
                    if (m.containsKey(subdomain)) {
                        List<String> str = m.get(subdomain);
                        str.add(url);
                        m.put(subdomain, str);
                    } else {
                        m.put(subdomain, List.of(url));
                    }

                }
            } catch (Exception e) {
                System.out.println("ERROR: " + url + "\t -> " + e.getMessage());
            }
            System.out.println("[" + counter + "] " + url + " fetch and saved (queue size:" + queue.size() + ").");
        }
        htmlFileManager.writeToFile(m);
        System.out.println("Operation complete");
    }
    public void audio(String domain, String domainAddress)
    {
        try
        {
            List<String> htmlLines = HtmlFetcherr.fetchHtml(domainAddress);
            List<String> urls = HtmlParser.getAllUrlsFromList(htmlLines);
            String line=String.join("\n",urls);
            urls.forEach(x -> {
                if (x.contains(domain) && x != null && !x.isEmpty()) {
                    queue.add(x);
                }
            });
            List<String> strings=new ArrayList<>();
            Pattern p=Pattern.compile("<(audio|source)[^>]+(src|data-src)\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>", Pattern.CASE_INSENSITIVE);
            Matcher m=p.matcher(line);
            while(m.find())
            {
                String src=m.group(1);
                try
                {
                    URL u=new URL(src);
                    strings.add(u.toString());
                }
                catch(MalformedURLException e)
                {
                    try
                    {
                        URL absoluteUrl = new URL(new URL(domainAddress), src);
                        strings.add(absoluteUrl.toString());
                    }
                    catch(MalformedURLException err)
                    {
                        System.out.println(err.getMessage());
                    }
                }
            }
            if(!strings.isEmpty())
                htmlFileManager.saveAudios(strings);
            else
                System.out.println("no available audio!");

        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void image(String domain, String domainAddress)
    {
        try {
            List<String> htmlLines = HtmlFetcherr.fetchHtml(domainAddress);
            String html = String.join("\n", htmlLines);

            // الگو برای یافتن تگ‌های img و استخراج src
            Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(html);

            List<String> imageUrls = new ArrayList<>();
            while (matcher.find()) {
                String src = matcher.group(1);
                try {
                    URL url = new URL(src);
                    imageUrls.add(src);
                } catch (MalformedURLException e) {
                    try {
                        URL absoluteUrl = new URL(new URL(domainAddress), src);
                        imageUrls.add(absoluteUrl.toString());
                    } catch (MalformedURLException e2) {
                        System.err.println("Invalid image URL: " + src);
                    }
                }
            }

            if (!imageUrls.isEmpty()) {
                htmlFileManager.saveImages(imageUrls);
            } else {
                System.out.println("No images found!");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

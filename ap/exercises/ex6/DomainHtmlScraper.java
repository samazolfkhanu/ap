package ap.exercises.ex6;


import ap.exercises.ex5.HtmlFetcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;
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
        //this.htmlFileManager.save(htmlLines);

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

}

package ap.exercises.ex6;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class DomainHtmlScraper {
    private String domainAddress;
    private Queue<String> queue;
    private HtmlFileManager htmlFileManager;
    private Set<String> visitedUrls;

    public DomainHtmlScraper(String domainAddress, String savePath) {
        this.domainAddress = domainAddress;
        this.queue = new LinkedList<>();
        this.htmlFileManager = new HtmlFileManager(savePath);
        this.visitedUrls = new HashSet<>();
    }

    public void start() throws IOException {
        if (domainAddress.endsWith("/")) {
            domainAddress = domainAddress.substring(0, domainAddress.length() - 1);
        }

        List<String> htmlLines = HtmlFetcherr.fetchHtml(domainAddress);
        this.htmlFileManager.save(htmlLines, domainAddress);
        visitedUrls.add(domainAddress);

        List<String> urls = HtmlParser.getAllUrlsFromList(htmlLines);
        List<String> sameDomainUrls = urls.stream()
                .filter(url -> isSameDomain(url))
                .collect(Collectors.toList());

        queue.addAll(new HashSet<>(sameDomainUrls));
        int counter = 1;

        while (!queue.isEmpty() && counter < 100) {
            String url = queue.remove();
            if (visitedUrls.contains(url)) {
                continue;
            }

            counter++;
            try {
                htmlLines = HtmlFetcherr.fetchHtml(url);
                this.htmlFileManager.save(htmlLines, url);
                visitedUrls.add(url);

                urls = HtmlParser.getAllUrlsFromList(htmlLines);
                // Filter URLs to only include same domain
                sameDomainUrls = urls.stream()
                        .filter(u -> isSameDomain(u))
                        .collect(Collectors.toList());

                queue.addAll(new HashSet<>(sameDomainUrls));
            } catch (Exception e) {
                System.out.println("ERROR: " + url + "\t -> " + e.getMessage());
            }
            System.out.println("[" + counter + "] " + url + " fetch and saved (queue size:" + queue.size() + ").");
        }
        System.out.println("Operation complete");
    }

    private boolean isSameDomain(String url) {
        if (url == null || url.isEmpty()) {
            return false;
        }

        try {
            URL u = new URL(url);
            String host = u.getHost();

            // Check if it's the main domain or a subdomain
            return host.equals(new URL(domainAddress).getHost()) ||
                    host.endsWith("." + new URL(domainAddress).getHost());
        } catch (Exception e) {
            return false;
        }
    }
}
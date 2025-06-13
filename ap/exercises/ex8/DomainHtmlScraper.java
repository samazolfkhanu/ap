package ap.exercises.ex8;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainHtmlScraper {
    private String domainAddress;
    private Queue<String> queue;
    private HtmlFileManager htmlFileManager;
    private int threadCount;

    public DomainHtmlScraper(String domainAddress, String savePath, int threadCount) {
        this.domainAddress = domainAddress;

        this.queue = new LinkedList<>();
        this.htmlFileManager = new HtmlFileManager(savePath);
        this.threadCount = threadCount;
    }

    public void start(String domain) throws IOException {
        List<String> htmlLines = HtmlFetcherr.fetchHtml(domainAddress);
        List<String> urls = HtmlParser.getAllUrlsFromList(htmlLines);

        Set<String> uniqueUrls = new HashSet<>();
        for (String url : urls) {
            if (url != null && !url.isEmpty() && url.contains(domain)) {
                uniqueUrls.add(url);
            }
        }

        if (threadCount <= 0) {
            for (String url : uniqueUrls) {
                processUrl(url);
            }
        } else {
            List<Thread> threads = new ArrayList<>();
            List<String> urlList = new ArrayList<>(uniqueUrls);
            int partSize = Math.max(urlList.size() / threadCount, 1);

            for (int i = 0; i < threadCount; i++) {
                int start = i * partSize;
                int end = Math.min(start + partSize, urlList.size());
                List<String> part = urlList.subList(start, end);

                Thread t = new Thread(() -> {
                    for (String url : part) {
                        processUrl(url);
                    }
                });

                threads.add(t);
                t.start();
            }

            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
            }
        }
        System.out.println("All URLs processed.");
    }

    private void processUrl(String url) {
        try {
            List<String> htmlLines = HtmlFetcherr.fetchHtml(url);
            htmlFileManager.save(htmlLines);
            System.out.println("Fetched and saved: " + url);
        } catch (Exception e) {
            System.out.println("Error fetching: " + url + " - " + e.getMessage());
        }

    }

    public void audio(String domain, String domainAddress) {
        try {
            List<String> htmlLines = HtmlFetcherr.fetchHtml(domainAddress);
            List<String> urls = HtmlParser.getAllUrlsFromList(htmlLines);
            String line = String.join("\n", urls);
            List<String> strings = new ArrayList<>();
            Pattern p = Pattern.compile("<(?:audio|source)[^>]+(?:src|data-src)\\s*=\\s*['\"]([^'\"]+)['\"]", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(line);

            while (m.find()) {
                String src = m.group(1);
                try {
                    URL u = new URL(src);
                    strings.add(u.toString());
                } catch (MalformedURLException e) {
                    try {
                        URL absoluteUrl = new URL(new URL(domainAddress), src);
                        strings.add(absoluteUrl.toString());
                    } catch (MalformedURLException err) {
                        System.out.println(err.getMessage());
                    }
                }
            }
            if (!strings.isEmpty()) {
                htmlFileManager.saveAudios(strings);
            } else {
                System.out.println("No available audio!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
        public void image(String domain, String domainAddress) {
            try {
                List<String> htmlLines = HtmlFetcherr.fetchHtml(domainAddress);
                String html = String.join("\n", htmlLines);
                Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"]", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(html);
                List<String> imageUrls = new ArrayList<>();
                while (matcher.find()) {
                    String src = matcher.group(1);
                    try {
                        URL url = new URL(src);
                        imageUrls.add(url.toString());
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
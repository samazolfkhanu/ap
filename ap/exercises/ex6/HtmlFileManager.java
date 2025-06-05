package ap.exercises.ex6;

import java.io.PrintWriter;
import java.util.List;

public class HtmlFileManager {

    private String saveFileBasePath;
    private static int saveCounter = 0;
    private String domainAddress;

    public HtmlFileManager(String saveFileBasePath) {
        this.saveFileBasePath = saveFileBasePath;
        DirectoryTools.createDirectory(saveFileBasePath);
    }

    public void save(List<String> lines, String url) {
        try {
            String relativePath = getRelativePath(url);
            String fullPath = saveFileBasePath + "/" + relativePath;
            DirectoryTools.createDirectory(fullPath);

            String fileName = getFileName(url);
            String saveHtmlFileAddress = fullPath + "/" + fileName;

            PrintWriter out = new PrintWriter(saveHtmlFileAddress);
            for (String line : lines) {
                out.println(line);
            }
            out.close();

            System.out.println("Saved: " + saveHtmlFileAddress);
        } catch (Exception e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    private String getRelativePath(String url) {
        // Remove protocol and domain
        String path = url.replaceFirst("^https?://", "");
        path = path.replaceFirst(domainAddress.replaceFirst("^https?://", ""), "");

        // Handle subdomains
        String subdomain = "";
        if (url.contains("://") && !url.startsWith(domainAddress)) {
            String temp = url.replaceFirst("^https?://", "");
            String mainDomain = domainAddress.replaceFirst("^https?://", "");
            subdomain = temp.substring(0, temp.indexOf(mainDomain) - 1);
            subdomain = "" + subdomain.replace(".", "");
        }

        // Get path parts
        String[] parts = path.split("/");
        StringBuilder relativePath = new StringBuilder();
        if (!subdomain.isEmpty()) {
            relativePath.append(subdomain);
        }

        for (int i = 0; i < parts.length - 1; i++) {
            if (!parts[i].isEmpty()) {
                if (relativePath.length() > 0) {
                    relativePath.append("/");
                }
                relativePath.append(parts[i]);
            }
        }

        return relativePath.toString();
    }

    private String getFileName(String url) {
        if (url.endsWith("/")) {
            return "index.html";
        }
        String[] parts = url.split("/");
        String lastPart = parts[parts.length - 1];
        if (lastPart.contains(".")) {
            return lastPart;
        }
        return lastPart + ".html";
    }
}
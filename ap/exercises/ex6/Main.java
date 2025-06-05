package ap.exercises.ex6;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String domainAddress = "https://znu.ac.ir";
        String savePath = Conf.SAVE_DIRECTORY;

        DomainHtmlScraper domainHtmlScraper = new DomainHtmlScraper(domainAddress, savePath);
        domainHtmlScraper.start();
    }
}
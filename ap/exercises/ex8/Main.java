package ap.exercises.ex8;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
    String domainAddress = Conf.DOMAIN_ADDRESS;
    String savePath = Conf.SAVE_DIRECTORY;
    int threadCount = ConfigReader.getThreadCount("F:/JavaProject/ap/exercises/ex8/download_config.txt");
        DomainHtmlScraper domainHtmlScraper = new DomainHtmlScraper(domainAddress, savePath, threadCount);
        domainHtmlScraper.audio("www", "https://education.znu.ac.ir/");
        domainHtmlScraper.image("www", "https://education.znu.ac.ir/");
        domainHtmlScraper.start(new URL(domainAddress).getHost());
}
}
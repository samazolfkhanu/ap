package ap.exercises.ex6;

import java.io.IOException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {

        String domainAddress = Conf.DOMAIN_ADDRESS;
        String savePath = Conf.SAVE_DIRECTORY;
        URL u=new URL(domainAddress);
        DomainHtmlScraper domainHtmlScraper = new DomainHtmlScraper(domainAddress,savePath);
        domainHtmlScraper.audio("www","https://education.znu.ac.ir/");
        domainHtmlScraper.image("www","https://education.znu.ac.ir/");
        domainHtmlScraper.start(u.getHost().toString());
    }
}

package ap.exercises.ex6;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String domainAddress = ap.exercises.ex5.Conf.DOMAIN_ADDRESS;
        String savePath = ap.exercises.ex5.Conf.SAVE_DIRECTORY;

        ap.exercises.ex5.DomainHtmlScraper domainHtmlScraper = new ap.exercises.ex5.DomainHtmlScraper(domainAddress,savePath);

        domainHtmlScraper.start();
    }
}

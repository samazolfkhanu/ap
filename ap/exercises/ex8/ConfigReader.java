package ap.exercises.ex8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigReader {

    public static int getThreadCount(String configPath) {
        try {
            Scanner s = new Scanner(new File(configPath));

            while (s.hasNextLine()) {
                String line = s.nextLine().trim();

                if (line.startsWith("Thread-count=")) {
                    String value = line.substring("Thread-count=".length()).trim();
                    return Integer.parseInt(value);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Config file not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing thread count: " + e.getMessage());
        }
        return 0;
    }
    public static void main(String[] args) {
        String configPath = "F:/JavaProject/ap/exercises/ex8/download_config.txt";
        int threadCount = getThreadCount(configPath);
        System.out.println("Thread count is: " + threadCount);
    }
}
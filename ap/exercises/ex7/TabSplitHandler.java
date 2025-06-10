package ap.exercises.ex7;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class TabSplitHandler<T extends HashId> implements StorageHandler<T> {
    @Override
    public void save(T item, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath + ".txt", true))) {
            writer.println(item.toTabSeparatedString());
        } catch (IOException e) {
            System.err.println("Error saving to tab-split file: " + e.getMessage());
        }
    }

    @Override
    public Map<Long, T> load(String filePath) {
        Map<Long, T> items = new TreeMap<>();
        File file = new File(filePath + ".txt");

        if (!file.exists()) {
            return items;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T item = parseTabSeparatedLine(line);
                if (item != null) {
                    items.put(item.getId(), item);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from tab-split file: " + e.getMessage());
        }
        return items;
    }

    @Override
    public void clear(String filePath) {
        new File(filePath + ".txt").delete();
    }

    private T parseTabSeparatedLine(String line) {
        return null;
    }
}
package ap.exercises.ex7;

import com.google.gson.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class JsonHandler<T extends HashId> implements StorageHandler<T> {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new JsonDeserializer() {
                public LocalDate deserialize(JsonElement json, java.lang.reflect.Type typeOfT,
                                             JsonDeserializationContext context) {
                    return LocalDate.parse(json.getAsString());
                }
            })
            .registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {
                public JsonElement serialize(LocalDate src, java.lang.reflect.Type typeOfSrc,
                                             JsonSerializationContext context) {
                    return new JsonPrimitive(src.toString());
                }
            })
            .create();
    private final Class<T> type;

    public JsonHandler(Class<T> type) {
        this.type = type;
    }

    @Override
    public void save(T item, String filePath) {
        try (FileWriter writer = new FileWriter(filePath + ".json", true)) {
            gson.toJson(item, writer);
            writer.write("\n");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Map<Long, T> load(String filePath) {
        Map<Long, T> items = new TreeMap<>();
        File file = new File(filePath + ".json");

        if (!file.exists()) {
            return items;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T item = gson.fromJson(line, type);
                if (item != null) {
                    items.put(item.getId(), item);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from JSON file: " + e.getMessage());
        }
        return items;
    }

    @Override
    public void clear(String filePath) {
        new File(filePath + ".json").delete();
    }
}
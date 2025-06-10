package ap.exercises.ex7;
import java.util.Map;

public interface StorageHandler<T extends HashId> {
    void save(T item, String tableName);
    Map<Long, T> load(String tableName);
    void clear(String tableName);
}

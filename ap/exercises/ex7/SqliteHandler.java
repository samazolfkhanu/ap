package ap.exercises.ex7;

import com.google.gson.Gson;

import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

public class SqliteHandler<T extends HashId> implements StorageHandler<T> {
    private final String dbUrl;
    private final Gson gson = new Gson();
    private final Class<T> type;

    public SqliteHandler(String dbPath, Class<T> type) {
        this.dbUrl = "jdbc:sqlite:" + dbPath;
        this.type = type;
        initializeDatabase();
    }

    private void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(dbUrl);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS library_data " +
                    "(table_name TEXT, id INTEGER, data TEXT, PRIMARY KEY (table_name, id))");
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    @Override
    public void save(T item, String tableName) {
        String sql = "INSERT OR REPLACE INTO library_data (table_name, id, data) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tableName);
            pstmt.setLong(2, item.getId());
            pstmt.setString(3, gson.toJson(item));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving to database: " + e.getMessage());
        }
    }

    @Override
    public Map<Long, T> load(String tableName) {
        Map<Long, T> items = new TreeMap<>();
        String sql = "SELECT id, data FROM library_data WHERE table_name = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tableName);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    T item = gson.fromJson(rs.getString("data"), type);
                    items.put(rs.getLong("id"), item);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error loading from database: " + e.getMessage());
        }
        return items;
    }

    @Override
    public void clear(String tableName) {
        String sql = "DELETE FROM library_data WHERE table_name = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tableName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error clearing table: " + e.getMessage());
        }
    }
}

import java.sql.*;
import java.util.Arrays;

public class DBUtil {


    public static Connection connect(String dbName) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false&serverTimezone=UTC&characterEncoding=UTF8",
                    "root",
                    "coderslab");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //    # dodawanie użytkownika,
//    INSERT INTO users (email, username, password) VALUES (?,?,?);

    //
    public static void insert(Connection conn, String query, String... params) {
        try (PreparedStatement preStmt = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                preStmt.setString(i + 1, params[i]);

            }
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //# zmiana danych,
//    UPDATE users SET email=?, username=?, password=? WHERE id=?;
//
    public static void updateOfId(Connection conn, String query, String email, String username, String password, int id) {
        try (PreparedStatement preStmt = conn.prepareStatement(query)) {
            preStmt.setString(1, email);
            preStmt.setString(2, username);
            preStmt.setString(3, password);
            preStmt.setInt(4, id);
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//# pobieranie wszystkich użytkowników.
//            SELECT * FROM users;

//# pobieranie po id,
//    SELECT * FROM users WHERE id = ?;
//

    public static void printData(Connection conn, String query, String... columnNames) {

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                for (String param : columnNames) {
                    System.out.println(resultSet.getString(param));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getIdFromDatabase(Connection conn, String query) {

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static String[] readRows(Connection conn, String query, String... columnNames) {
        String[] dataFromRow = new String[0];

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            resultSet.next();
            for (int j = 0; j < columnNames.length; j++) {
                dataFromRow = Arrays.copyOf(dataFromRow, dataFromRow.length + 1);
                dataFromRow[dataFromRow.length - 1] = resultSet.getString(j + 1);
            }
            return dataFromRow;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] getTabOfOneColumn(Connection conn, String query, String columnName) {
        int[] tabOfOneColumn = new int[0];

        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                tabOfOneColumn = Arrays.copyOf(tabOfOneColumn, tabOfOneColumn.length + 1);
                tabOfOneColumn[tabOfOneColumn.length - 1] = resultSet.getInt(columnName);
            }
            return tabOfOneColumn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//# usuwanie po id,
//    DELETE FROM users WHERE id = ?;
//

    private static final String DELETE_QUERY = "DELETE FROM tableName where id = ?";

    public static void remove(Connection conn, String tableName, int id) {
        try (PreparedStatement statement =
                     conn.prepareStatement(DELETE_QUERY.replace("tableName", tableName));) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
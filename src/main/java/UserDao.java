import DAO.DBUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.sql.*;
import java.util.Arrays;

public class UserDao {

    public static Connection connectToWorkshop2() {
        try (Connection connToWorkshop2 = DBUtil.connect("workshop2")) {
            return connToWorkshop2;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private final String CREATE_USER_QUERY = "INSERT INTO users (email, username, password) VALUES (?,?,?);";
    private final String GET_ID_USER_QUERY = "SELECT id FROM users WHERE email LIKE 'unique_email';";

    public User create(User user) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement preStmt = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, user.getEmail());
            preStmt.setString(2, user.getUsername());
            preStmt.setString(3, user.getPassword());
            preStmt.executeUpdate();
           // user.setId(getIdFromDatabase(conn, GET_ID_USER_QUERY.replace("unique_email", user.getEmail())));
            ResultSet resultSet = preStmt.getGeneratedKeys();
            if (resultSet.next()){
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getIdFromDatabase(Connection conn, String query) {
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static final String READ_USER_QUERY = "SELECT email, username, password FROM users WHERE id =?";

    public User read(int userId) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement preStmt = conn.prepareStatement(READ_USER_QUERY);
            preStmt.setString(1, String.valueOf(userId));
            ResultSet resultSet = preStmt.executeQuery();
            resultSet.next();
            String email = resultSet.getString("email");

            String username = resultSet.getString("username");

            String password = resultSet.getString("password");
            return new User(userId, email, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String UPDATE_USER_QUERY = "UPDATE users SET email=?, username=?, password=? WHERE id=?;";

    public void update(User user) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement prepStmt = conn.prepareStatement(UPDATE_USER_QUERY);
            prepStmt.setString(1, user.getEmail());
            prepStmt.setString(2, user.getUsername());
            prepStmt.setString(3, user.getPassword());
            prepStmt.setInt(4, user.getId());
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String FIND_ALL_USERS_QUERY = "SELECT id FROM users";

    public User[] findAll() {
        int[] tabOfId = new int[0];
        User[] users = new User[0];
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement prepStmt = conn.prepareStatement("SELECT id FROM users");
            ResultSet resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                tabOfId = Arrays.copyOf(tabOfId, tabOfId.length + 1);
                tabOfId[tabOfId.length - 1] = resultSet.getInt("id");
            }
            for (int id :
                    tabOfId) {
                users = ArrayUtils.add(users, read(id));
            }
            System.out.println(Arrays.toString(users));
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id=?";

    public void delete(int idUser) {
        try (Connection conn = DBUtil.connect("workshop2")) {
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER_QUERY);
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

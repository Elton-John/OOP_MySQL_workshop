import org.apache.commons.lang3.ArrayUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDao {

    public static Connection connectToWorkshop2() {
        try (Connection connToWorkshop2 = DBUtil.connect("workshop2")) {
            return connToWorkshop2;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    Metoda przyjmuje obiekt klasy
//, który powinien posiadać wypełnione
//    atrybuty, (userName,
//    email, password),
//    id natomiast pozostaje niewypełniony.
//    Metoda ma zwracać obiekt z uzupełnionym identykatorem.
//    W ramach metody należy:
//    zapisać do bazy danych informacje z obiektu
//    pobrać id nowo zapisanego użytkownika
//    uzupełnić id w obiekcie
//    zwrócić uzupełniony obiekt

    public User create(User user) {
        String queryInsert = "INSERT INTO users (email, username, password) VALUES (?,?,?);";
        DBUtil.insert(connectToWorkshop2(), queryInsert, user.getEmail(), user.getUsername(), user.getPassword());
        String queryGetId = "SELECT id FROM users WHERE email LIKE '" + user.getEmail() + "';";
        int id = DBUtil.getIdFromDatabase(connectToWorkshop2(), queryGetId);
        user.setId(id);
        return user;
    }

//    Służyć ma ona do odczytania użytkownika dla zadanego identykatora. Jej sygnatura jest
//    następująca:
//    Metoda przyjmuje identykator na podstawie, którego należy w bazie danych pobrać wiersz.
//    W ramach metody należy wykonać:
//    pobrać z bazy danych wiersz dla zadanego identykatora
//    utworzyć nowy obiekt klasy user
//    uzupełnić obiekt danymi z bazy
//    zwrócić uzupełniony obiek

    public User read(int userId) {
        return getNewUserOfId(userId, connectToWorkshop2());
    }

    private User getNewUserOfId(int userId, Connection connection) {
        String query = "SELECT email, username, password FROM users WHERE id = " + userId + ";";
        String[] data = DBUtil.readRows(connectToWorkshop2(), query, "email", "username", "password");
        return new User(userId, data[0], data[1], data[2]);
    }

//    Służyć ma ona do odczytania użytkownika dla zadanego identykatora. Jej sygnatura jest
//    następująca:
//    Metoda przyjmuje obiekt klasy
//   //, który powinien posiadać wypełnione
//    atrybuty, (userName,
//    email, password, id),
//    Metoda nic nie zwraca.
//    W ramach metody należy zmienić dane w bazie na podstawie danych z obiektu.

    public void update(User user) {
        String query = "UPDATE users SET email=?, username=?, password=? WHERE id=?;";
        DBUtil.updateOfId(connectToWorkshop2(), query, user.getEmail(), user.getUsername(), user.getPassword(), user.getId());
    }

//    ramach metody należy wykonać:
//    pobrać z bazy danych wszystkie wiersze z tabeli users
//    na podstawie każdego wiersza utworzyć obiekt klasy
//         obiekty umieścić w tablicy
//    zwrócić tablicę obiektów
//    Będziemy również potrzebować mechanizmu, który pozwoli nam automatycznie powiększać tablicę

    public User[] findAll() {
        User[] users = new User[0];
        String query = "SELECT id FROM users";
        int[] tabOfId = DBUtil.getTabOfOneColumn(connectToWorkshop2(), query, "id");
        for (int userId :
                tabOfId) {
            User user = getNewUserOfId(userId, connectToWorkshop2());
            user.setId(userId);
            users = ArrayUtils.add(users, user);
        }
        for (User userDate :
                users) {
            System.out.println(userDate);
        }
        return users;
    }

//    ma ona do odczytania użytkownika dla zadanego identykatora. Jej sygnatura jest
//    następująca:
//    Metoda przyjmuje identykator na podstawie, którego należy w bazie danych pobrać wiersz.
//    Metoda nic nie zwraca.
//    W ramach metody należy usunąć wiersz z bazy danych na podstawie przekazanego identykatora

    public void delete(int idUser) {
        DBUtil.remove(connectToWorkshop2(), "users", idUser);
    }
}

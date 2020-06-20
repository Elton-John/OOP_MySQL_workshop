import org.apache.commons.lang3.ArrayUtils;

import java.sql.Connection;

public class UserDao {

    public static Connection connectToWorkshop2() {
        Connection connToWorkshop2 = DBUtil.connect("workshop2");
        return connToWorkshop2;
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

    public static User create(User user) {
        Connection connToWorkshop2 = DBUtil.connect("workshop2");
        String queryInsert = "INSERT INTO users (email, username, password) VALUES (?,?,?);";
        DBUtil.insert(connToWorkshop2, queryInsert, user.getEmail(), user.getUsername(), user.getPassword());
        String queryGetId = "SELECT id FROM users WHERE email LIKE '" + user.getEmail() + "';";
        int id = DBUtil.getIdFromDatabase(connToWorkshop2, queryGetId);
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

    public static User read(int userId) {
        Connection connToWorkshop2 = DBUtil.connect("workshop2");
        User user = getNewUserOfId(userId, connToWorkshop2);
        return user;
    }

    private static User getNewUserOfId(int userId, Connection connToWorkshop2) {
        String query = "SELECT email, username, password FROM users WHERE id = " + userId + ";";
        String[] data = DBUtil.readRows(connToWorkshop2, query, "email", "username", "password");
        return new User(data[0], data[1], data[2]);
    }

//    Służyć ma ona do odczytania użytkownika dla zadanego identykatora. Jej sygnatura jest
//    następująca:
//    Metoda przyjmuje obiekt klasy
//   //, który powinien posiadać wypełnione
//    atrybuty, (userName,
//    email, password, id),
//    Metoda nic nie zwraca.
//    W ramach metody należy zmienić dane w bazie na podstawie danych z obiektu.

    public static void update(User user) {
        Connection connToWorkshop2 = DBUtil.connect("workshop2");
        String query = "UPDATE users SET email=?, username=?, password=? WHERE id=?;";
        DBUtil.updateOfId(connToWorkshop2, query, user.getEmail(), user.getUsername(), user.getPassword(), user.getId());
    }

//    ramach metody należy wykonać:
//    pobrać z bazy danych wszystkie wiersze z tabeli users
//    na podstawie każdego wiersza utworzyć obiekt klasy
//         obiekty umieścić w tablicy
//    zwrócić tablicę obiektów
//    Będziemy również potrzebować mechanizmu, który pozwoli nam automatycznie powiększać tablicę

    public static User[] findAll() {
        //Connection connToWorkshop2 = DBUtil.connect("workshop2");
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
}

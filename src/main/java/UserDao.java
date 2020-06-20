import java.sql.Connection;

public class UserDao {

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
        String query = "SELECT email, username, password FROM users WHERE id = " + userId + ";";
        String[] data = DBUtil.readRows(connToWorkshop2, query, "email", "username", "password");
        User user = new User(data[0], data[1], data[2]);
        return user;
    }

}

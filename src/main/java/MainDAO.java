import java.sql.Connection;
import java.util.Arrays;

public class MainDAO {
    public static void main(String[] args) {

        UserDao userDao = new UserDao();
//        User user = new User();
//        user.setEmail("krokodyl@gmail.com");
//        user.setUsername("Krokos-kokos");
//        user.setPassword("pass123");
//        userDao.create(user);

//        User userToUpdate = userDao.read(3);
//        userToUpdate.setUsername("Arkadiusz");
//        userToUpdate.setEmail("arek@coderslab.pl");
//        userToUpdate.setPassword("superPassword");
//        userDao.update(userToUpdate);

        userDao.delete(1);
        User[] all = userDao.findAll();

//        Connection connectToWorkshop2 = DBUtil.connect("workshop2");
//        String query = "UPDATE users SET email=?, username=?, password=? WHERE id=?;";
//        DBUtil.updateOfId(connectToWorkshop2,query,"toni@mail", "tonya", "ljljljl", "1");
//        Connection connToWorkshop2 =  DBUtil.connect("workshop2");
//
//        String queryGetId = "SELECT id FROM users WHERE email LIKE 'toni@mail'";
//        int id = DBUtil.getIdFromDatabase(connToWorkshop2,queryGetId);
//        System.out.println(id);

//        User user1 = new User("kuku@mail", "kukułka", "ljljlj");
//       UserDao.create(user1);
//        System.out.println(user1.getId());

//       User user1 = UserDao.read(1);
//        System.out.println(user1.getId()+user1.getEmail()+user1.getUsername()+user1.getPassword());
//
//        User user2 = new User(3,"gogo@mail", "gosza", "1236546");
//        UserDao.update(user2);
//
//        Connection connToWorkshop2 = DBUtil.connect("workshop2");
//
//        String query = "SELECT id FROM users";
//        int[] tabOfId = DBUtil.getTabOfOneColumn(connToWorkshop2,query,"id");
//        System.out.println(Arrays.toString(tabOfId));
//
        // UserDao.findAll();
        //System.out.println(Arrays.toString(UserDao.findAll()));
//        UserDao.delete(7);
    }
}

public class MainDAO {
    public static void main(String[] args) {

        UserDao userDao = new UserDao();

        User user = new User();
        user.setEmail("krokodyl@gmail.com");
        user.setUsername("Krokos-kokos");
        user.setPassword("pass123");
        userDao.create(user);

        User userToUpdate = userDao.read(10);
        userToUpdate.setUsername("Arkadiusz");
        userToUpdate.setEmail("arek@coderslab.pl");
        userToUpdate.setPassword("superPassword");
        userDao.update(userToUpdate);

        userDao.delete(10);
        User[] all = userDao.findAll();

    }
}

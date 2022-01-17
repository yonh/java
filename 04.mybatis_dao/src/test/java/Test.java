import com.yonh.dao.UserDao;
import com.yonh.dao.UserDaoImpl;
import com.yonh.pojo.User;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        findAll();
        saveUser();
        updateUser();
        deleteUser();
    }

    private static UserDao getUserDao() throws IOException {
        UserDao userDao = new UserDaoImpl();
        return userDao;
    }

    private static void saveUser() throws IOException {
        UserDao userDao = getUserDao();
        User user = new User(3, "new_user", "new_user");
        userDao.saveUser(user);
    }

    private static void updateUser() throws IOException {
        UserDao userDao = getUserDao();
        User user = new User(3, "update_user", "update_user");
        userDao.updateUser(user);
    }

    private static void deleteUser() throws IOException {
        UserDao userDao = getUserDao();
        userDao.deleteUser(3);
    }

    private static void findAll() throws IOException {
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.findAll();
        System.out.println(users);
    }
}

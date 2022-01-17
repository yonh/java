import com.yonh.dao.UserDao;
import com.yonh.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        findAll();
        saveUser();
        updateUser();
        deleteUser();

        findByCondition();
    }

    private static UserDao getUserDao() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);

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
        UserDao userDao = getUserDao();
        List<User> users = userDao.findAll();
        System.out.println(users);
    }

    private static void findByCondition() throws IOException {
        UserDao userDao = getUserDao();
        User user = new User(1,null,null);
        List<User> users = userDao.findByCondition(user);
        System.out.println("findByCondition:");
        System.out.println(users);
    }
}

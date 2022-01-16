import com.yonh.dao.IUserDao;
import com.yonh.dao.UserDaoImpl;
import com.yonh.io.Resources;
import com.yonh.pojo.User;
import com.yonh.sqlSession.SqlSession;
import com.yonh.sqlSession.SqlSessionFactory;
import com.yonh.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws PropertyVetoException, DocumentException, SQLException, IntrospectionException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        IUserDao userDao = new UserDaoImpl();

        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        User u = userDao.findByCondition(user);
        System.out.printf("userDao.findByCondition: %s\n", u);

        List<User> list = userDao.findAll();
        System.out.printf("userDao.findAll: %s\n", list);
    }
}

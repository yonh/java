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
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        User u = sqlSession.selectOne("user.selectOne", user);
        System.out.printf("select one: %s\n", u);

        List<Object> list = sqlSession.selectList("user.selectList", user);
        System.out.printf("select list: %s\n", list);
    }
}

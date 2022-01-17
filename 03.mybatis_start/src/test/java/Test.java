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
    }

    private static SqlSession getSession() throws IOException {
        // 配置文件的加载，将配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 解析配置文件，并框架SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 借助SqlSessionFactory生成SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 默认开启一个事务，但是该事务不会默认提交，在进行增删改时需要手动提交，或传入参数true开启自动提交
        return sqlSession;
    }

    private static void saveUser() throws IOException {
        SqlSession sqlSession = getSession();
        User user = new User(3, "new_user", "new_user");
        sqlSession.insert("User.saveUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    private static void updateUser() throws IOException {
        SqlSession sqlSession = getSession();
        User user = new User(3, "update_user", "update_user");
        sqlSession.update("User.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    private static void deleteUser() throws IOException {
        SqlSession sqlSession = getSession();
        sqlSession.delete("User.deleteUser", 3);
        sqlSession.commit();
        sqlSession.close();
    }

    private static void findAll() throws IOException {
        SqlSession sqlSession = getSession();
        List<User> users = sqlSession.selectList("User.findAll");
        System.out.println(users);
        sqlSession.close();
    }
}

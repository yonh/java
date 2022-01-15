import com.yonh.io.Resources;
import com.yonh.sqlSession.SqlSession;
import com.yonh.sqlSession.SqlSessionFactory;
import com.yonh.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) throws PropertyVetoException, DocumentException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
    }
}

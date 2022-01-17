import com.yonh.mapper.OrderMapper;
import com.yonh.mapper.UserMapper;
import com.yonh.pojo.Order;
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
//        findAll();
//        saveUser();
//        updateUser();
//        deleteUser();
//
//        findByCondition();
//        findByIds();
//
//        findOrders();
        findUsersAndOrder();
    }

    private static UserMapper getUserMapper() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        return userMapper;
    }

    private static OrderMapper getOrderMapperg() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        return orderMapper;
    }

    private static void saveUser() throws IOException {
        UserMapper userMapper = getUserMapper();
        User user = new User(3, "new_user", "new_user");
        userMapper.saveUser(user);
    }

    private static void updateUser() throws IOException {
        UserMapper userMapper = getUserMapper();
        User user = new User(3, "update_user", "update_user");
        userMapper.updateUser(user);
    }

    private static void deleteUser() throws IOException {
        UserMapper userMapper = getUserMapper();
        userMapper.deleteUser(3);
    }

    private static void findAll() throws IOException {
        UserMapper userMapper = getUserMapper();
        List<User> users = userMapper.findAll();
        System.out.println(users);
    }

    private static void findByIds() throws IOException {
        UserMapper userMapper = getUserMapper();
        int[] ids = {1,2};
        List<User> users = userMapper.findByIds(ids);
        System.out.println("findByIds:");
        System.out.println(users);
    }

    private static void findByCondition() throws IOException {
        UserMapper userMapper = getUserMapper();
        User user = new User(1,null,null);
        List<User> users = userMapper.findByCondition(user);
        System.out.println("findByCondition:");
        System.out.println(users);
    }

    private static void findOrders() throws IOException {
        OrderMapper orderMapper = getOrderMapperg();
        List<Order> orders = orderMapper.findAll();
        System.out.println("findOrders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private static void findUsersAndOrder() throws IOException {
        UserMapper userMapper = getUserMapper();
        List<User> users = userMapper.findAllAndOrders();
        System.out.println("findAllAndOrders:");
        for (User user : users) {
            System.out.println(user);
        }
    }

}

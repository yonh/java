package com.yonh.dao;

import com.yonh.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static SqlSession getSession(boolean autoCommit) throws IOException {
        // 配置文件的加载，将配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 解析配置文件，并框架SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 借助SqlSessionFactory生成SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 默认开启一个事务，但是该事务不会默认提交，在进行增删改时需要手动提交，或传入参数true开启自动提交
        return sqlSession;
    }

    @Override
    public List<User> findAll() throws IOException {
        SqlSession sqlSession = getSession(true);
        List<User> users = sqlSession.selectList("User.findAll");
        return users;
    }

    @Override
    public boolean saveUser(User user) throws IOException {
        SqlSession sqlSession = getSession(true);
        int ret = sqlSession.insert("User.saveUser", user);
        sqlSession.close();
        return ret == 1;
    }

    @Override
    public boolean updateUser(User user) throws IOException {
        SqlSession sqlSession = getSession(true);
        int ret = sqlSession.update("User.updateUser", user);
        return ret == 1;
    }

    @Override
    public boolean deleteUser(int id) throws IOException {
        SqlSession sqlSession = getSession(true);
        int ret = sqlSession.delete("User.deleteUser", id);
        return ret == 1;
    }
}
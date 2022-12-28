package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.ParameterMapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sherry
 * @date 2022/12/25 14:17
 */
public class ParameterMapperTest {
    /**
     * MyBatis 获取值的两种方式;
     * ${} 按照字符串拼接;
     * #{} 本质占位符赋值;
     */
    @Test
    public void testSelect() throws IOException {
        BasicConfigurator.configure();
        //读取MyBatis的核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        //获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //通过代理模式创建ParameterMapper r接口的代理实现类对象
        ParameterMapper  Mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = Mapper.GetUserByUsername("admit");
        System.out.println(user);
    }

    @Test
    public void testJDBC() throws Exception{
        String username = "admit";
        Class<?> aClass = Class.forName("");
        Connection connection = DriverManager.getConnection("com.mysql.jdbc.Driver","root","111111");
        connection.prepareStatement("select * from t_user where username = '" +username+ "' ");
        PreparedStatement statement = connection.prepareStatement("select * from t_user where username =?");
        statement.setString(1,username); // 通过占位符进行赋值;
    }

    @Test
    public void testDeLu() throws Exception{
        String username = "admit";
        String password = "111111";
        Class<?> aClass = Class.forName("");
        Connection connection = DriverManager.getConnection("com.mysql.jdbc.Driver","root","111111");
        connection.prepareStatement("select * from t_user where username = '" +username+ "'");
    }

    @Test
    public void testSelectLogo() throws IOException {
        BasicConfigurator.configure();
        //读取MyBatis的核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");

        //获取SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        //通过核心配置文件所对应的字节输入流创建工厂类SqlSessionFactory，生产SqlSession对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //通过代理模式创建ParameterMapper r接口的代理实现类对象
        ParameterMapper  Mapper = sqlSession.getMapper(ParameterMapper.class);
        /**
         * 验证登录元素;
         */
        User user = Mapper.checkLogin("admit","111111");
        System.out.println(user);
    }

    @Test
    public void checkLoginByMap() throws IOException {
        BasicConfigurator.configure();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ParameterMapper  Mapper = sqlSession.getMapper(ParameterMapper.class);


        Map<String,Object> map = new HashMap<>();
        map.put("usermane","root");
        map.put("password","111111");
        User user = Mapper.checkLoginByMap(map);
        System.out.println(user);
    }

    @Test
    public void insertUser() throws IOException {
        BasicConfigurator.configure();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ParameterMapper  Mapper = sqlSession.getMapper(ParameterMapper.class);
        Mapper.insertUser(new User(null,"Tom","123456",12,"男","123@321.com"));
    }

    @Test
    public void checkLoginByParam() throws IOException {
        BasicConfigurator.configure();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        ParameterMapper  Mapper = sqlSession.getMapper(ParameterMapper.class);
        Mapper.CheckLoginByParam("admin","123456");
    }



}

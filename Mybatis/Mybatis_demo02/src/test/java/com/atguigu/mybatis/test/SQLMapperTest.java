package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SQLMapper;
import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sherry
 * @date 2022/12/28 15:15
 */
public class SQLMapperTest {
    @Test
    public void TestSqlMapper() throws IOException {
        BasicConfigurator.configure();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SQLMapper Mapper = sqlSession.getMapper(SQLMapper.class);
        List<User> list = Mapper.getUserByLike("a");
        System.out.println(list);

        int result = Mapper.deleteMore("1,2,3");
        System.out.println(result);

        //获取表名;
        List<User> list1 = Mapper.getUserTableName("t_user");
        System.out.println(list1);

        User user = new User(null, "ton", "123", 23, "男", "123@321.com");
        Mapper.insertUser(user);
        System.out.println(user);
    }

    @Test
    public void TestJdbc() throws SQLException, ClassNotFoundException {
        Class.forName("");
        Connection connection = DriverManager.getConnection("","","");
        PreparedStatement ps = connection.prepareStatement("insert",Statement.NO_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
    }
}

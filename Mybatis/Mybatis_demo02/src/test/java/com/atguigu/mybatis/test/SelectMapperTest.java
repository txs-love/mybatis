package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SelectMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sherry
 * @date 2022/12/28 0:21
 */
public class SelectMapperTest {
    /**
   1. 如果查询出的数据只有一条，可以通过
     * 	1. 实体类对象接收
     * 	2. List集合接收
     * 	3. Map集合接收，结果`{password=123456, sex=男, id=1, age=23, username=admin}`
   2. 如果查询出的数据有多条，一定不能用实体类对象接收，会抛异常TooManyResultsException，可以通过
     * 	1. 实体类类型的List集合接收
     * 	2. Map类型的List集合接收
     * 	3. 在mapper接口的方法上添加@MapKey注解
     */
    @Test
    public void TestGetUserById() throws IOException {
        BasicConfigurator.configure();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        SelectMapper Mapper = sqlSession.getMapper(SelectMapper.class);

        /**User user = (User) Mapper.getUserById(1);
        System.out.println(user);
        */

        //    System.out.println(Mapper.getAllUser());

        //System.out.println(Mapper.getCount());
        //System.out.println(Mapper.getAllUserToMap());
        //System.out.println(Mapper.getAllUserToMap1());
    }
}

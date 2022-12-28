package com.atguigu.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sherry
 * @date 2022/12/25 14:19
 */
public class SQLSessionUtils {
    public static SqlSession getSqlSession(){
        SqlSession sqlSession =null;
        try{
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            sqlSession = sqlSessionFactory.openSession(true);

        }catch (IOException e){
            e.printStackTrace();
        }
        return sqlSession;
    }
}

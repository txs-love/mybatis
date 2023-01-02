import com.atguigu.mybatis.mapper.DeptMapper;
import com.atguigu.mybatis.mapper.DynamicSQLMapper;
import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sherry
 * @date 2022/12/31 20:40
 */
public class DynamicSQLTest {
    @Test
    public void getEmpByCondition() throws IOException {
        BasicConfigurator.configure();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        DynamicSQLMapper Mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> emp = Mapper.getEmpByCondition(new Emp(null, "李四", 32, "男", "123@qq.com"));
        System.out.println(emp);

        //测试GetEmpByChoose
        List<Emp> empByChoose = Mapper.getEmpByChoose(new Emp(null, "李四", 32, "男", "123@qq.com"));
        System.out.println(empByChoose);

        //批量删除
        int result = Mapper.deleteMoreByArray(new Integer[]{6, 7, 8, 9});
        System.out.println(result);

        //批量添加
        Emp emp1 = new Emp(null,"a",1,"男","123@321.com",null);
        Emp emp2 = new Emp(null,"b",1,"男","123@321.com",null);
        Emp emp3 = new Emp(null,"c",1,"男","123@321.com",null);
        List<Emp> emps = Arrays.asList(emp1, emp2, emp3);
        int result1 = Mapper.insertMoreByList(emps);
        System.out.println(result1);

    }
}
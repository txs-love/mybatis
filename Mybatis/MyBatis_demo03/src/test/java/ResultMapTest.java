import com.atguigu.mybatis.mapper.DeptMapper;
import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;
import utils.SQLSessionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Sherry
 * @date 2022/12/28 21:21
 */
public class ResultMapTest {
    /**
     * 若字段名和实体类中的属性名不一致，但是字段名符合数据库的规则（使用_），实体类中的属性名符合Java的规则（使用驼峰）。此时也可通过以下两种方式处理字段名和实体类中的属性的映射关系
     * 	1. 可以通过为字段起别名的方式，保证和实体类中的属性名保持一致
     * 		<!--List<Emp> getAllEmp();-->
     * 		<select id="getAllEmp" resultType="Emp">
     * 			select eid,emp_name empName,age,sex,email from t_emp
     * 		</select>
     * 	2. 可以在MyBatis的核心配置文件中的`setting`标签中，设置一个全局配置信息mapUnderscoreToCamelCase，
     * 	可以在查询表中数据时，自动将_类型的字段名转换为驼峰，例如：字段名user_name，设置了mapUnderscoreToCamelCase，此时字段名就会转换为userName;
     *     <settings>
     *         <setting name="mapUnderscoreToCamelCase" value="true"/>
     *     </settings>
     * @throws IOException
     */
    @Test
    public void getAllEmpTest() throws IOException {
        BasicConfigurator.configure();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmpMapper  Mapper = sqlSession.getMapper(EmpMapper.class);
        EmpMapper  MapperDept = (EmpMapper) sqlSession.getMapper(DeptMapper.class);

        List<Emp> list = Mapper.getAllEmp();
        list.forEach(emp -> System.out.println(emp));

        Emp emp = Mapper.getEmpAndDept(1);
        System.out.println(emp);

        Emp emp1 = Mapper.getEmpAndDeptByStepOne(1);
        System.out.println(emp.getEmpName());

        Emp emp2 = Mapper.getEmpAndDeptByStepOne(1);
        System.out.println(emp.getEmpName());
        System.out.println("----------------");
        System.out.println(emp.getDept());

        //TestGetDeptAndEmp
        Emp dept = MapperDept.getEmpAndDept(1);
        System.out.println(dept);
    }
}

import com.atguigu.mybatis.mapper.CacheMapper;
import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sherry
 * @date 2023/1/1 13:18
 */
public class CacheTest {
    public void cache() throws IOException {
        /**
         * 使一级缓存失效的四种情况：
         * 	1. 不同的SqlSession对应不同的一级缓存
         * 	2. 同一个SqlSession但是查询条件不同
         * 	3. 同一个SqlSession两次查询期间执行了任何一次增删改操作
         * 	4. 同一个SqlSession两次查询期间手动清空了缓存
         */
        BasicConfigurator.configure();
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        /**
         * SqlSessionFactory级别，
         */
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        CacheMapper Mapper1 = sqlSession1.getMapper(CacheMapper.class);
        CacheMapper Mapper2 = sqlSession2.getMapper(CacheMapper.class);
        Emp emp1 = Mapper1.getEmpByEid(1);
        System.out.println(emp1);

        Emp emp2 = Mapper2.getEmpByEid(1);
        System.out.println(emp2);

        Mapper1.insertEmp(new Emp(null, "李四", 32, "男", "123@qq.com"));
        System.out.println();
        /**
         * 1. 在核心配置文件中，设置全局配置属性cacheEnabled="true"，默认为true，不需要设置
         * 	2. 在映射文件中设置标签<cache />
         * 	3. 二级缓存必须在SqlSession关闭或提交之后有效
         * 	4. 查询的数据所转换的实体类类型必须实现序列化的接口
         */
        sqlSession1.clearCache();   //关闭
        sqlSession1.commit();       //提交
    }

    /**
       先查询二级缓存，因为二级缓存中可能会有其他程序已经查出来的数据，可以拿来直接使用
          如果二级缓存没有命中，再查询一级缓存
          如果一级缓存也没有命中，则查询数据库
          SqlSession关闭之后，一级缓存中的数据会写入二级缓存
     */
    public void cacheTwo() {
        try {
            BasicConfigurator.configure();
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            CacheMapper Mapper1 = sqlSession1.getMapper(CacheMapper.class);
            Emp emp1 = Mapper1.getEmpByEid(1);
            System.out.println(emp1);

            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            CacheMapper Mapper2 = sqlSession2.getMapper(CacheMapper.class);
            Emp emp2 = Mapper2.getEmpByEid(1);
            System.out.println(emp2);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            System.exit(0);
        }
        /**
         * 在mapper配置文件中添加的cache标签可以设置一些属性
          eviction属性：缓存回收策略
                - LRU（Least Recently Used） – 最近最少使用的：移除最长时间不被使用的对象。
            	- FIFO（First in First out） – 先进先出：按对象进入缓存的顺序来移除它们。
            	- SOFT – 软引用：移除基于垃圾回收器状态和软引用规则的对象。
                - WEAK – 弱引用：更积极地移除基于垃圾收集器状态和弱引用规则的对象。
              默认的是 LRU
         flushInterval属性：刷新间隔，单位毫秒
            	- 默认情况是不设置，也就是没有刷新间隔，缓存仅仅调用语句（增删改）时刷新
                - size属性：引用数目，正整数
                - 代表缓存最多可以存储多少个对象，太大容易导致内存溢出
         readOnly属性：只读，true/false
             	- true：只读缓存；会给所有调用者返回缓存对象的相同实例。因此这些对象不能被修改。这提供了很重要的性能优势。
            	- false：读写缓存；会返回缓存对象的拷贝（通过序列化）。这会慢一些，但是安全，因此默认是false
         */
    }
}
package com.atguigu.mybatis.mapper;
import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author Sherry
 * @date 2022/12/31 20:33
 */
public interface DynamicSQLMapper {
    /**
     * 多条件查询
     */
    List<Emp> getEmpByCondition(Emp emp);

    /**
     * 测试choose、when、otherwise标签
     * @param emp
     * @return
     */
    List<Emp> getEmpByChoose(Emp emp);

    /**
     * 批量删除
     */
    int deleteMoreByArray(@Param("eids") Integer[] eids);
    /**
     * 批量添加
     */
    int insertMoreByList(@Param("emps") List<Emp> emps);

    /**
     * 设置SQL片段
     */
    List<Emp> getEmpByCondition1(Emp emp);
}

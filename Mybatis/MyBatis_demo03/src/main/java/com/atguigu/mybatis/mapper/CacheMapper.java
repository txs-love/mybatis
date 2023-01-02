package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

/**
 * @author Sherry
 * @date 2023/1/1 13:14
 */
public interface CacheMapper {
    Emp getEmpByEid(@Param("eid") Integer eid);

    void insertEmp(Emp emp);

}

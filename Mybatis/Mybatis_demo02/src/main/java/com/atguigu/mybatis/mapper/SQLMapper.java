package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author Sherry
 * @date 2022/12/28 15:12
 */
public interface SQLMapper {
    /**
     * 根据用户名进行模糊查询
     * @param username
     * @return java.util.List<com.atguigu.mybatis.pojo.User>
     * @date 2022/2/26 21:56
     */
    List<User> getUserByLike(@Param("username") String username);

    /**
     * 根据id批量删除
     * @param ids
     * @return int
     */
    int deleteMore(@Param("ids") String ids);

    /**
     * 查询指定表当中的数据
     */
    List<User> getUserTableName(@Param("tableName") String tableName);

    /**
     * 添加用户信息
     * @param user
     */
    void insertUser(User user);
}

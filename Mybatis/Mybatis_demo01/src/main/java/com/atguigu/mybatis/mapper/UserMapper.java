package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;

/**
 * @author Sherry
 * @date 2022/12/24 12:08
 * | 类 | 表 |
 * | 属性 | 字段/列 |
 * | 对象 | 记录/行 |

 映射文件的命名规则
 * 	- 表所对应的实体类的类名+Mapper.xml
 * 	- 例如：表t_user，映射的实体类为User，所对应的映射文件为UserMapper.xml;因此一个映射文件对应一个实体类，对应一张表的操作
 * 	- MyBatis映射文件用于编写SQL，访问以及操作表中的数据
 * 	- MyBatis映射文件存放的位置是src/main/resources/mappers目录下
MyBatis中可以面向接口操作数据，要保证两个一致
 * 	- mapper接口的全类名和映射文件的命名空间（namespace）保持一致
 * 	- mapper接口中方法的方法名和映射文件中编写SQL的标签的id属性保持一致
 */
public interface UserMapper {
    /**
     * 添加用户信息
     */
    int insertUser();

    /**
     * 修改用户信息;
     */
    void updateUser();

    /**
     * 删除用户信息;
     */
    void deleteUser();

    /**
     * 根据id查询用户信息和查询全部;
     */
    User getUserId();
    User getUserAll();
}

package cn.exrick.xcloud.base.dao;

import cn.exrick.xcloud.base.entity.User;
import cn.exrick.xcloud.common.base.BaseXCloudDao;

import java.util.List;

/**
 * @author Exrickx
 */
public interface UserDao extends BaseXCloudDao<User,String> {

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    List<User> findUserByUsername(String username);
}

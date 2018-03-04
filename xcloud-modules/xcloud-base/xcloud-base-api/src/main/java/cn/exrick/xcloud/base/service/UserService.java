package cn.exrick.xcloud.base.service;

import cn.exrick.xcloud.base.entity.User;
import cn.exrick.xcloud.common.base.BaseXCloudService;

/**
 * @author Exrickx
 */
public interface UserService extends BaseXCloudService<User,String> {

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}

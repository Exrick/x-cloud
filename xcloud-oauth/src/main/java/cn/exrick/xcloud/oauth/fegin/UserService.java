package cn.exrick.xcloud.oauth.fegin;

import cn.exrick.xcloud.base.entity.User;
import cn.exrick.xcloud.oauth.fegin.fallback.UserServiceImplFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Exrickx
 */
@FeignClient(name = "base-server",fallback = UserServiceImplFallback.class)
public interface UserService {

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    @RequestMapping(value = "/user/findUserByUsername/{username}", method = RequestMethod.GET)
    User findUserByUsername(@PathVariable("username") String username);
}

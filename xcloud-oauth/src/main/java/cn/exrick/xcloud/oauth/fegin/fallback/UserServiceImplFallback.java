package cn.exrick.xcloud.oauth.fegin.fallback;

import cn.exrick.xcloud.base.entity.User;
import cn.exrick.xcloud.oauth.fegin.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Exrickx
 */
@Slf4j
@Service
public class UserServiceImplFallback implements UserService {

    @Override
    public User findUserByUsername(String username) {
        log.error("调用{}异常:{}", "findUserByUsername", username);
        return null;
    }
}

package cn.exrick.xcloud.oauth.fegin;

import cn.exrick.xcloud.oauth.fegin.fallback.UserServiceImplFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Exrickx
 */
@FeignClient(name = "base-server",fallback = UserServiceImplFallback.class)
public interface UserService {
}

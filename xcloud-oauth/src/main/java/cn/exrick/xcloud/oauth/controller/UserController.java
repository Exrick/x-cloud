package cn.exrick.xcloud.oauth.controller;

import cn.exrick.xcloud.common.utils.ResultUtil;
import cn.exrick.xcloud.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@RestController
@Api(description = "OAuth用户相关接口")
public class UserController {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiOperation("获取当前登录用户信息")
    public Result<Object> user(Principal user) {
        return new ResultUtil<Object>().setData(user);
    }

    /**
     * 清除Redis中accessToken和refreshToken
     * @param accessToken
     * @param refreshToken
     * @return
     */
    @RequestMapping(value = "/removeToken", method = RequestMethod.GET)
    @ApiOperation("清除accessToken和refreshToken")
    public Result<Object> removeToken(String accessToken, String refreshToken) {

        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.removeRefreshToken(refreshToken);
        tokenStore.removeAccessToken(accessToken);
        return new ResultUtil<Object>().setSuccessMsg("清除Token成功");
    }
}

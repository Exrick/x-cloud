package cn.exrick.xcloud.base.controller;

import cn.exrick.xcloud.base.entity.User;
import cn.exrick.xcloud.base.service.UserService;
import cn.exrick.xcloud.common.base.BaseXCloudController;
import cn.exrick.xcloud.common.utils.ResultUtil;
import cn.exrick.xcloud.common.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Exrickx
 */
@Slf4j
@RestController
@Api(description = "管理员接口")
@RequestMapping("/user")
public class UserController extends BaseXCloudController<User, String> {

    @Autowired
    private UserService userService;

    @Override
    public UserService getService() {
        return userService;
    }

    @RequestMapping(value = "/getUserByUsername", method = RequestMethod.GET)
    @ApiOperation("通过用户名获取用户")
    public Result<Object> getUserByUsername(@RequestParam String username){

        User u=userService.findUserByUsername(username);
        return new ResultUtil().setData(u);
    }
}

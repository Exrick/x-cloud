package cn.exrick.xcloud.oauth.service;

import cn.exrick.xcloud.base.entity.User;
import cn.exrick.xcloud.oauth.fegin.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Exrickx
 */
@Slf4j
@Service("UserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService, Serializable {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userService.findUserByUsername(username);
        return null;
    }
}

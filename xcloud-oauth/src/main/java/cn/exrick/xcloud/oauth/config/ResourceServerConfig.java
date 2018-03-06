package cn.exrick.xcloud.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * oauth-server本身也是一个resource server
 * @author Exrickx
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //所有请求都需经过认证和授权
                .authorizeRequests().anyRequest().authenticated()
                //禁用csrf为了方便，否则退出链接必须要发送一个带csrf_token的post请求
                .and()
                .csrf().disable()
                .httpBasic();
    }
}

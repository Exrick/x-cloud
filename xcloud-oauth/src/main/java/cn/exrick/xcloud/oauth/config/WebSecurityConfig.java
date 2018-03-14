package cn.exrick.xcloud.oauth.config;

import cn.exrick.xcloud.oauth.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Exrickx
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailService);
        //用户不退出登录会记住登录状态
        auth.eraseCredentials(false);
    }

    /**
     * 不定义没有password grant_type
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();

        //除配置文件忽略路径其它所有请求都需经过认证和授权
        for(String url:ignoredUrlsProperties.getUrls()){
            registry.antMatchers(url).permitAll();
        }

        registry.anyRequest().authenticated()
                .and()
                //禁用csrf为了方便，否则退出链接必须要发送一个带csrf_token的post请求
                .csrf().disable();
    }

}

package cn.exrick.xcloud.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @author Exrickx
 */
@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {

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

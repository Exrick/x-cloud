package cn.exrick.xcloud.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

/**
 * OAuth-server提供user信息 所以本身也是一个Resource server
 * @author Exrickx
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

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

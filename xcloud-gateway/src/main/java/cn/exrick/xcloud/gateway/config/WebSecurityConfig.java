package cn.exrick.xcloud.gateway.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Exrickx
 */
@Configuration
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.
                antMatcher("/**")
                //所有请求都得经过认证和授权
                .authorizeRequests().anyRequest().authenticated()
                .and().authorizeRequests().antMatchers("/","/anon").permitAll()
                .and()
                //禁用csrf为了方便,否则退出链接必须要发送一个带csrf_token的post请求
                .csrf().disable()
                // 退出的URL
                .logout().logoutUrl("/logout").permitAll()
                // 退出成功后跳转路径
                .logoutSuccessUrl("/login");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        //过滤
        web.ignoring().antMatchers("*.css","*.js","*.html","*.ico","*.json","*.png","/druid/*");
    }
}

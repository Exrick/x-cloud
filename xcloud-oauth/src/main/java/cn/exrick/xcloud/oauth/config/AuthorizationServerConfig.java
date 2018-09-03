package cn.exrick.xcloud.oauth.config;

import cn.exrick.xcloud.common.constant.CommonConstant;
import cn.exrick.xcloud.common.constant.OAuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author Exrickx
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${xcloud.oauth.clientId}")
    private String clientId;

    @Value("${xcloud.oauth.secret}")
    private String secret;

    @Value("${xcloud.oauth.scope}")
    private String scope;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        log.info("开始加载OAuth");
        //配置一个客户端
        clients.inMemory()
                .withClient(clientId)
                .secret(secret)
                //指定客户端允许的grant_types 这里提供授权码、密码模式
                .authorizedGrantTypes(OAuthConstant.AUTHORIZATION_CODE, OAuthConstant.PASSWORD, OAuthConstant.REFRESH_TOKEN)
                //权限范围 可选项
                .scopes(scope).
                //自动授权 不需授权确认操作
                autoApprove(true);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .accessTokenConverter(jwtAccessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {

        oauthServer
                //允许表单认证
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(OAuthConstant.SIGN_KEY);
        return jwtAccessTokenConverter;
    }
}

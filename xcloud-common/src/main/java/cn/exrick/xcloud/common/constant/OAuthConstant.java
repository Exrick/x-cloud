package cn.exrick.xcloud.common.constant;

/**
 * @author Exrickx
 */
public interface OAuthConstant {

    /**
     * token请求头名称
     */
    String REQ_HEADER = "Authorization";

    /**
     * token分割符
     */
    String TOKEN_SPLIT = "Bearer ";

    /**
     * jwt签名
     */
    String SIGN_KEY = "xcloud";

    /**
     * 授权码模式
     */
    String AUTHORIZATION_CODE = "authorization_code";

    /**
     * 密码模式
     */
    String PASSWORD = "password";

    /**
     * 更新token
     */
    String REFRESH_TOKEN = "refresh_token";
}

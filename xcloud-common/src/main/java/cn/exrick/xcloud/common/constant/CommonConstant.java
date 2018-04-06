package cn.exrick.xcloud.common.constant;

/**
 * @author Exrickx
 */
public interface CommonConstant {

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
     * 待启用/激活
     */
     Integer STATUS_NORMAL = 0;

    /**
     * 已启用/激活
     */
     Integer STATUS_ACTIVE = 1;

    /**
     * 已锁定禁用
     */
     Integer STATUS_LOCK = -1;

    /**
     * 删除标记
     */
    Integer STATUS_DEL = 1;

    /**
     * 用户类型
     */
    Integer USER_STATUS_NORMAL = 0;
}

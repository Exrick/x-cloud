package cn.exrick.xcloud.common.constant;

/**
 * @author Exrickx
 */
public class CommonConstant {

    /**
     * token请求头名称
     */
    public static final String REQ_HEADER = "Authorization";

    /**
     * token分割符
     */
    public static final String TOKEN_SPLIT = "Bearer ";

    /**
     * jwt签名
     */
    public static final String SIGN_KEY = "xcloud";

    /**
     * 待启用激活
     */
    public static final Integer STATUS_NORMAL = 0;

    /**
     * 已启用激活
     */
    public static final Integer STATUS_ACTIVE = 1;

    /**
     * 已锁定禁用
     */
    public static final Integer STATUS_LOCK = 2;

    /**
     * 删除标记
     */
    public static final Integer STATUS_DEL = -1;
}

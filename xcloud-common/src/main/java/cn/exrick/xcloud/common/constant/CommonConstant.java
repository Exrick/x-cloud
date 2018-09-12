package cn.exrick.xcloud.common.constant;

/**
 * 常量
 * @author Exrickx
 */
public interface CommonConstant {

    /**
     * 用户默认头像
     */
    String USER_DEFAULT_AVATAR = "https://s1.ax1x.com/2018/05/19/CcdVQP.png";

    /**
     * 用户正常状态
     */
    Integer USER_STATUS_NORMAL = 0;

    /**
     * 用户禁用状态
     */
    Integer USER_STATUS_LOCK = -1;

    /**
     * 普通用户
     */
    Integer USER_TYPE_NORMAL = 0;

    /**
     * 管理员
     */
    Integer USER_TYPE_ADMIN = 1;

    /**
     * 性别男
     */
    Integer SEX_MAN = 0;

    /**
     * 性别女
     */
    Integer SEX_WOMAN = 1;

    /**
     * 性别保密
     */
    Integer SEX_SECRET = 2;

    /**
     * 正常状态
     */
    Integer STATUS_NORMAL = 0;

    /**
     * 禁用状态
     */
    Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    Integer DEL_FLAG = 1;

    /**
     * 限流标识
     */
    String LIMIT_ALL = "XBOOT_LIMIT_ALL";

    /**
     * 页面类型权限
     */
    Integer PERMISSION_PAGE = 0;

    /**
     * 操作类型权限
     */
    Integer PERMISSION_OPERATION = 1;

    /**
     * 1级菜单父id
     */
    String PARENT_ID = "0";

    /**
     * 1级菜单
     */
    Integer LEVEL_ONE = 1;

    /**
     * 2级菜单
     */
    Integer LEVEL_TWO = 2;

    /**
     * 3级菜单
     */
    Integer LEVEL_THREE = 3;

    /**
     * 消息发送范围
     */
    Integer MESSAGE_RANGE_ALL = 0;

    /**
     * 公告
     */
    Integer MESSAGE_TYPE_SYSTEM = 0;

    /**
     * 提醒
     */
    Integer MESSAGE_TYPE_REMIND = 1;

    /**
     * 私信
     */
    Integer MESSAGE_TYPE_PERSONAL = 2;

    /**
     * 未读
     */
    Integer MESSAGE_STATUS_UNREAD = 0;

    /**
     * 已读
     */
    Integer MESSAGE_STATUS_READ = 1;

    /**
     * github登录
     */
    Integer SOCIAL_TYPE_GITHUB = 0;

    /**
     * qq登录
     */
    Integer SOCIAL_TYPE_QQ = 1;

    /**
     * 微博登录
     */
    Integer SOCIAL_TYPE_WEIBO = 2;

    /**
     * 短信验证码key前缀
     */
    String PRE_SMS = "XBOOT_PRE_SMS:";
}

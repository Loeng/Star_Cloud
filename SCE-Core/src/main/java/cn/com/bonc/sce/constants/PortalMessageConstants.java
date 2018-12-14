package cn.com.bonc.sce.constants;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 17:35
 */
public interface PortalMessageConstants {
    String SCE_PORTAL_MSG_000 = MessageConstants.SCE_MSG_0000;

    /**
     * 登录相关
     * 100 - 200
     */
    String SCE_PORTAL_MSG_100 = "用户 {} 登录成功";
    String SCE_PORTAL_MSG_101 = "不支持的登录类型";

    /**
     * 通用错误信息
     */
    String SCE_PORTAL_MSG_500 = "服务器发生意外错误";
    String SCE_PORTAL_MSG_501 = "服务器发生意外错误,错误信息 {}";
}

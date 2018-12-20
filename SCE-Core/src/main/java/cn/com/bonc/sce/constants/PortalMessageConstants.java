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
     * 100 - 199
     */
    String SCE_PORTAL_MSG_100 = "不支持的登录类型";

    /**
     * 操作成功
     * 200 - 299
     */
    String SCE_PORTAL_MSG_200 = "操作成功";

    /**
     * 操作失败相关
     * 400 - 600
     */
    String SCE_PORTAL_MSG_401 = "用户没有权限执行此操作";

    String SCE_PORTAL_MSG_409 = "短信发送失败";
    String SCE_PORTAL_MSG_410 = "验证码验证失败";
    String SCE_PORTAL_MSG_411 = "手机短信验证失败";
    String SCE_PORTAL_MSG_412 = "安全码验证失败";

    /**
     * 通用错误信息
     */
    String SCE_PORTAL_MSG_500 = "服务器发生意外错误";
    String SCE_PORTAL_MSG_500_DEV = "服务器发生意外错误,错误信息 {}";
}

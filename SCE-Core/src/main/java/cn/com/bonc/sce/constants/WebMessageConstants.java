package cn.com.bonc.sce.constants;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 17:35
 */
public interface WebMessageConstants {
    String SCE_PORTAL_MSG_000 = MessageConstants.SCE_MSG_0000;

    /**
     * 登录相关
     * 100 - 199
     */
    String SCE_PORTAL_MSG_100 = "不支持的登录类型";
    String SCE_PORTAL_MSG_101 = "找不到用户";

    /**
     * 操作成功
     * 200 - 299
     */
    String SCE_PORTAL_MSG_200 = "操作成功";

    String SCE_PORTAL_MSG_240 = "文件上传成功";

    /**
     * 操作失败相关
     * 400 - 600
     */
    String SCE_PORTAL_MSG_401 = "用户没有权限执行此操作";

    String SCE_PORTAL_MSG_409 = "短信发送失败";
    String SCE_PORTAL_MSG_410 = "验证码验证失败";
    String SCE_PORTAL_MSG_411 = "手机短信验证失败";
    String SCE_PORTAL_MSG_412 = "安全码验证失败";
    String SCE_PORTAL_MSG_413 = "家长验证失败";

    String SCE_PORTAL_MSG_420 = "数据读取失败";
    String SCE_PORTAL_MSG_421 = "数据修改失败";
    String SCE_PORTAL_MSG_422 = "数据删除失败";
    String SCE_PORTAL_MSG_423 = "数据添加失败";

    String SCE_PORTAL_MSG_430 = "学生与家长信息不匹配";

    String SCE_PORTAL_MSG_450 = "上传文件为空";
    String SCE_PORTAL_MSG_451 = "上传文件失败";
    String SCE_PORTAL_MSG_452 = "上传文件类型异常";

    /**
     * 通用错误信息
     */
    String SCE_PORTAL_MSG_500 = "服务器发生意外错误";
    String SCE_PORTAL_MSG_501 = "服务器发生意外错误,错误信息 {}";
}

package cn.com.bonc.sce.constants;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 17:35
 */
public interface WebMessageConstants {
    /**
     * 通用信息提示
     * 0 - 99
     */
    String SCE_PORTAL_MSG_000 = MessageConstants.SCE_MSG_0000;

    String SCE_WEB_MSG_001 = "参数  {} 长度不可为 0";
    String SCE_WEb_MSG_002 = "缺少 {} 参数";

    /**
     * 登录相关
     * 100 - 199
     */
    String SCE_PORTAL_MSG_100 = "不支持的登录类型";
    String SCE_PORTAL_MSG_101 = "用户名或密码错误";
    String SCE_PORTAL_MSG_102 = "用户名或密码错误";
    String SCE_PORTAL_MSG_103 = "账户已停用";
    String SCE_PORTAL_MSG_104 = "账户已注销";

    String SCE_WEB_MSG_120 = "用户 ticket 过期或无效";

    String SCE_WEB_MSG_150 = "无效或过期的token";
    String SCE_WEB_MSG_151 = "缺少应用 ID 或 TOKEN 信息";
    String SCE_WEB_MSG_152 = "应用 ID 或 TOKEN 无效";
    String SCE_WEB_MSG_153 = "找不到用户";
    String SCE_WEB_MSG_154 = "系统中已有该账号";
    String SCE_WEB_MSG_155 = "账号不能为空";


    String SCE_PORTAL_MSG_110 = "没有关联的厂商";
    /**
     * 操作成功
     * 200 - 299
     */
    String SCE_PORTAL_MSG_200 = "操作成功";
    String SCE_PORTAL_MSG_240 = "文件上传成功";

    String SCE_PORTAL_MSG_250 = "添加用户成功";
    String SCE_PORTAL_MSG_251 = "添加学生用户成功";
    String SCE_PORTAL_MSG_252 = "添加教师用户成功";
    String SCE_PORTAL_MSG_253 = "添加学校用户成功";
    String SCE_PORTAL_MSG_254 = "添加厂家用户成功";
    String SCE_PORTAL_MSG_255 = "添加家长用户成功";
    String SCE_PORTAL_MSG_256 = "添加代理商用户成功";
    String SCE_PORTAL_MSG_257 = "添加机构用户成功";

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

    String SCE_PORTAL_MSG_430 = "学生与家长信息手机号不匹配";
    String SCE_PORTAL_MSG_431 = "缺失 %s 参数";
    String SCE_PORTAL_MSG_432 = "%s，在Excel中的第 %d 行";
    String SCE_PORTAL_MSG_433 = "不支持的分页参数";

    String SCE_PORTAL_MSG_440 = "您不是机构用户，无权进行此操作";

    String SCE_PORTAL_MSG_450 = "上传文件为空";
    String SCE_PORTAL_MSG_451 = "上传文件失败";
    String SCE_PORTAL_MSG_452 = "上传文件类型异常";
    String SCE_PORTAL_MSG_453 = "上传文件不在解析范围内";

    String SCE_PORTAL_MSG_470 = "下载应用失败";

    String SCE_PORTAL_MSG_480 = "该订单无法开票";
    String SCE_PORTAL_MSG_481 = "没有该订单";
    String SCE_PORTAL_MSG_482 = "订单号不能为空";
    String SCE_PORTAL_MSG_483 = "有无法开票的订单";

    String SCE_PORTAL_MSG_490 = "头条信息数量超过8条，无法添加已选新闻作为头条";

    /**
     * 通用错误信息
     */
    String SCE_PORTAL_MSG_500 = "服务器发生意外错误";
    String SCE_PORTAL_MSG_501 = "服务器发生意外错误,错误信息 {}";

    /**
     * 待办相关
     * 600 - 640
     */
    String SCE_WEB_MSG_600 = "需指定待办事项处理人";
    String SCE_WEB_MSG_601 = "不支持的待办类型";

    /**
     * 消息通知相关
     * 641-669
     */
    String SCE_PORTAL_MSG_641 = "您提交的应用[%s]%s已通过审核。";
    String SCE_PORTAL_MSG_642 = "您提交的应用[%s]%s未通过审核。";
    String SCE_PORTAL_MSG_643 = "您提交的应用[%s]%s未通过审核。原因为：%s";

    /* *
     * 优惠码相关
     * 740-760
     */
    String SCE_PORTAL_MSG_740 = "转换日期出错";
    String SCE_PORTAL_MSG_741 = "该优惠码不存在";
    String SCE_PORTAL_MSG_742 = "该优惠码已过期";
    String SCE_PORTAL_MSG_743 = "已超过该优惠码的使用次数";
    String SCE_PORTAL_MSG_744 = "该优惠码不适用此类商品";
}

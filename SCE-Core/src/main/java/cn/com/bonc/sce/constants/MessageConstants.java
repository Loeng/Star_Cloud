package cn.com.bonc.sce.constants;

/**
 * 服务间通信用 msg constants
 *
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 16:56
 */
public interface MessageConstants {
    /**
     * 1. 系统初始化相关
     * 0 - 199
     */
    String SCE_MSG_0000 = "服务 {} 启动, ip: {}, port: []";
    String SCE_MSG_0001 = "读取到公私钥自维护服务启动配置，初始化应用公私钥对, 更新周期: {} ms";
    String SCE_MSG_0002 = "公私钥自维护服务：  对应用公私钥进行更新";

    String SCE_MSG_0200 = "操作成功";

    /**
     * 2. 文件操作相关
     * 201 - 399
     */
    String SCE_MSG_0201 = "读取文件： {}";

    /**
     * 3. 数据库操作相关
     * 400 - 599
     */
    String SCE_MSG_406 = "数据读取失败";
    String SCE_MSG_407 = "数据修改失败";
    String SCE_MSG_408 = "数据删除失败";
    String SCE_MSG_409 = "数据添加失败";

    /**
     * 4. 网络异常
     * 600 - 799
     */

    /**
     * Spring 框架自身异常
     * 800 - 999
     */

    /**
     * 登录异常相关
     * 1000-1099
     */
    String SCE_MSG_1000 = "ip:{} 的用户尝试使用不支持的登录方式进行登录，使用登录方式: {}";
    String SCE_MSG_1001 = "用户: {} 在 ip: {} 尝试进行登录";
    String SCE_MSG_1002 = "未知IP";
    String SCE_MSG_1010 = "找不到用户 userId: [{}]";

    String SCE_MSG_1020 = "修改用户状态时找不到用户数据， userId: [{}]; changeStatus: [{}]";
    String SCE_MSG_1021 = "修改用户登录状态，是否第一次登录： {}， userId: [{}]";

    String SCE_MSG_1022 = "登录名已存在";
    String SCE_MSG_1023 = "手机号已被注册";
    String SCE_MSG_1024 = "用户名不存在";
    String SCE_MSG_1025 = "缺少参数";

    /**
     * 加密错误
     */
    String SCE_MSG_1100 = "解析到没有使用平台微服务指定加密方式进行加密的字符串";

    /**
     * 通用异常处理
     */
    String SCE_MSG_9999 = "服务器发生未捕获的异常，错误信息： {}";
}

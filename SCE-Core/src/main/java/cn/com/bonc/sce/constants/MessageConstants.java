package cn.com.bonc.sce.constants;

/**
 * 服务间通信用 msg constants
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

    /**
     * 2. 文件操作相关
     * 200 - 399
     */
    String SCE_MSG_0200 = "读取文件： {}";

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
     * 通用异常处理
     */
    String SCE_MSG_9999 = "服务器发生未捕获的异常，错误信息： {}";
}

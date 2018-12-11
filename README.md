[TOC]

# StarCloudEducation

本项目为福建星云教育大数据云平台后台服务项目。

服务基于 Spirng-Cloud 技术栈。

## Release notes

## 开发规约



### 变量命名

0. 方法、变量名命名采用驼峰命名法。
0. 常量采用全大写+‘_’的方式。
0. 方法名必须使用'动词+操作对象'方式。
0. 类命名全部采用大写，尽量避免使用中文。

### 服务间通信： 基于 Feign 的 Restful Web API

Restful 本身属于 WebService 的一种，是一种还原 HTTP 协议设计理念的一种**编程风格**，Restful 只是一种编写 WebService 网络接口的一种方式，而不是某种规范。

**Rest 的核心在于，将所有在网络中提供的信息都当做一种“资源” ，并通过不同的 HTTP method 来区分对资源的操作**：

- GET：读取（Read）
- POST：新建（Create）
- PUT：更新（Update）
- PATCH：更新（Update），通常是部分更新
- DELETE：删除（Delete）

比如，就处理用户数据为例:

```java
@Slf4j
@RequestMapping( "/users" )
@RestController
public class UserDataApiController {
    private UserService userService;

    @Autowired
    public UserDataApiController( UserService userService ) {
        this.UserService = userService;
    }

    /**
     * 根据用户 id 获取用户数据
     *
     * @param userId 用户 id
     * @return 用户数据
     */
    @GetMapping( "/{userId}" )
    @ResponseBody
    public RestRecord getUserById( @PathVariable( "userId" ) String userId ) {
        return new RestRecord( 0, userService.getUserById( userId ) );
    }

    /**
     * 获取所有用户的数据
     *
     * @return 用户数据 list
     */
    @GetMapping( "" )
    @ResponseBody
    public RestRecord getAllUsersInfo() {
        return new RestRecord( 0, userService.getAll() );
    }

    /**
     * 根据用户 id 删除用户数据，并不直接删除用户的注册信息，只是将用户的状态改为不可用
     *
     * @param userId 用户 id
     * @return 用户
     */
    @DeleteMapping( "/{userId}" )
    @ResponseBody
    public RestRecord deleteUserById( @PathVariable( "userId" ) String userId ) {
        return new RestRecord( 0, userService.deleteUserById( userId ) );
    }

    /**
     * 使用 Put/Patch 进行数据跟新
     *
     * @param user 用户信息
     * @return 待更新的用户
     */
    @PutMapping( "" )
    @ResponseBody
    public RestRecord updateUserInfo( User user ) {
        return new RestRecord( 0, userService.updateUser( user ) );
    }

    /**
     * patch 用作数据的局部跟新
     *
     * @param userId   用户id
     * @param username 需要修改的用户名称
     * @return 跟新是否成功
     */
    @PatchMapping( "/{userId}" )
    @ResponseBody
    public RestRecord updateUsername(
            @PathVariable( "userId" ) String userId,
            @RequestParam( "username" ) String username ) {
        return new RestRecord(0,userService.updateUserName( userId, username ));
    }

    /**
     * @param user 用户信息
     * @return 是否添加成功
     */
    @PostMapping( "" )
    @ResponseBody
    public RestRecord registerNewUser( User user ) {
        return new RestRecord( 0, userService.createUser( user ) );
    }
}
```

- code
    - 状态码，如果接口正常运作，则返回 0.
- msg
    - 对接口返回值的描述信息，如果接口执行过程中有错误，则需提供相关提示信息
- data
    - 任意类型的业务数据，可为空
- exception
    - 接口异常，可为空
- exceptionStackTrace
    - 如接口执行过程中抛出异常，则需为 RestRecord 注入 Exception，注入 Exception 过程中会自动注入 exceptionStackTrace，即错误的堆栈信息

### 使用 @Slf4j 注解引入 logger

直接在类上添加 @Slf4j 注解，即可直接在代码中直接使用 log 打印日志：

**需要在 idea 中安装 lombok 插件**

```java
@Slf4j
public class TestLogger {
    public static void main(String[] args) {
        log.info("测试一下");
    }
}
```
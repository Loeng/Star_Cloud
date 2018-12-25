//package cn.com.bonc.sce.api;
//
//import cn.com.bonc.sce.dao.UserDao;
//import cn.com.bonc.sce.model.User;
//import cn.com.bonc.sce.rest.RestRecord;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author Leucippus
// * @version 0.1
// * @since 2018/12/11 11:08
// */
//@Slf4j
//@RequestMapping( "/users" )
//@RestController
//public class UserDataApiController {
//    private UserDao userDao;
//
//    @Autowired
//    public UserDataApiController( UserDao userDao ) {
//        this.userDao = userDao;
//    }
//
//    /**
//     * 根据用户 id 获取用户数据
//     *
//     * @param userId 用户 id
//     * @return 用户数据
//     */
//    @GetMapping( "/{userId}" )
//    @ResponseBody
//    public RestRecord getUserById( @PathVariable( "userId" ) String userId ) {
////        return new RestRecord( 200, "测试数据",userDao.getUserById( userId ));
//    }
//
//    /**
//     * 获取所有用户的数据
//     *
//     * @return 用户数据 list
//     */
//    @GetMapping( "" )
//    @ResponseBody
//    public RestRecord getAllUsersInfo() {
//        return new RestRecord( 0, userDao.getAll() );
//    }
//
//    /**
//     * 根据用户 id 删除用户数据，并不直接删除用户的注册信息，只是将用户的状态改为不可用
//     *
//     * @param userId 用户 id
//     * @return 用户
//     */
//    @DeleteMapping( "/{userId}" )
//    @ResponseBody
//    public RestRecord deleteUserById( @PathVariable( "userId" ) String userId ) {
//        return new RestRecord( 0, userDao.deleteUserById( userId ) );
//    }
//
//    /**
//     * 使用 Put/Patch 进行数据跟新
//     *
//     * @param user 用户信息
//     * @return 待更新的用户
//     */
//    @PutMapping( "" )
//    @ResponseBody
//    public RestRecord updateUserInfo( User user ) {
//        return new RestRecord( 0, userDao.updateUser( user ) );
//    }
//
//    /**
//     * patch 用作数据的局部更新
//     *
//     * @param userId   用户id
//     * @param username 需要修改的用户名称
//     * @return 跟新是否成功
//     */
//    @PatchMapping( "/{userId}" )
//    @ResponseBody
//    public RestRecord updateUsername(
//            @PathVariable( "userId" ) String userId,
//            @RequestParam( "username" ) String username ) {
//        return new RestRecord(0,userDao.updateUserName( userId, username ));
//    }
//
//    /**
//     * @param user 用户信息
//     * @return 是否添加成功
//     */
//    @PostMapping( "" )
//    @ResponseBody
//    public RestRecord registerNewUser( User user ) {
//        return new RestRecord( 0, userDao.createUser( user ) );
//    }
//}

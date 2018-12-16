package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 7:46
 */
@ApiModel
public class User {
    @ApiModelProperty(value = "userId", name = "用户 id", example = "12345")
    private int userId;
    @ApiModelProperty(value = "username", name = "用户名", example = "RGM79")
    private String username;

    public User( int userId, String username ) {
        this.userId = userId;
        this.username = username;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId( int userId ) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }
}

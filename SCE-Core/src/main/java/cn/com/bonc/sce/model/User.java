package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/11 7:46
 */
@Getter
@Setter
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ApiModelProperty(value = "userId", name = "用户 id", example = "12345")
    private int userId;
    @ApiModelProperty(value = "username", name = "用户名", example = "RGM79")
    private String username;
    private String phoneNumber;
//    private String phoneNumber;
}

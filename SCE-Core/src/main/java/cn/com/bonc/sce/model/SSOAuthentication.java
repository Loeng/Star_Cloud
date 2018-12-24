package cn.com.bonc.sce.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author Leucippus
 * @version 0.1
 * @since 2018/12/22 15:06
 */
@Getter
@Setter
@ApiModel
@NoArgsConstructor
@AllArgsConstructor

public class SSOAuthentication {
    @ApiModelProperty( name = "验证类型 : 0/用户名密码登录，1/手机号登录, 2/邮箱登录", example = "0", required = true )
    private char authType;
    @ApiModelProperty( name = "身份信息标识符，和 authType 必须匹配，如 authType 为 0 则改值必须是用户名", example = "0", required = true )
    private String identifier;
    @ApiModelProperty( name = "密码", example = "$RX78-2GP03D", required = true )
    private String password;
    @ApiModelProperty( name = "接收身份令牌的接口地址", example = "http://www.mysite.com/authentication/ticket", required = true )
    private String ticketReceiveUrl;
    @ApiModelProperty( name = "需要和身份令牌一同传递的身份信息", example = "&extra0=IPhoneX&extra1=red", required = false )
    private String extraInfo;

}

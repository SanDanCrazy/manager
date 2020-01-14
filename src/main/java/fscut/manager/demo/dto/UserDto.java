package fscut.manager.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 用户对象
 */
@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = -9077975168976887742L;

    @ApiModelProperty(value = "用户名",example = "caiheng",required = true)
    private String username;
    @ApiModelProperty(value = "密码",example = "123456", required = true)
    private String password;
    @ApiModelProperty(hidden = true)
    private String encryptPwd;
    @ApiModelProperty(hidden = true)
    private Integer userId;
    @ApiModelProperty(hidden = true)
    private String salt;
    @ApiModelProperty(hidden = true)
    private List<Integer> productIds;
    @ApiModelProperty(hidden = true)
    private List<String> roles;


}

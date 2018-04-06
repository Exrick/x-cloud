package cn.exrick.xcloud.base.entity;

import cn.exrick.xcloud.common.base.BaseXCloudEntity;
import cn.exrick.xcloud.common.constant.CommonConstant;
import cn.exrick.xcloud.common.utils.AESUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * @author Exrickx
 */
@Data
@Entity
@Table(name = "t_user")
public class User extends BaseXCloudEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value = "唯一id")
    private String id = UUID.randomUUID().toString().replace("-","");

    @ApiModelProperty(value = "用户名")
    private String username;

    @JsonIgnore
    @Transient
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 数据库映射存入加密密码
     */
    @Column(name="password")
    @ApiModelProperty(value = "密码(加密)")
    private String cryptPassword;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "0女 1男 2保密")
    private Integer sex;

    @ApiModelProperty(value = "头像链接")
    private String avatar;

    @ApiModelProperty(value = "类型 默认0普通用户")
    private Integer type = CommonConstant.USER_STATUS_NORMAL;

    @ApiModelProperty(value = "状态 默认1启用/激活 0待启用 -1禁用")
    private Integer status = CommonConstant.STATUS_ACTIVE;

    @ApiModelProperty(value = "描述备注")
    private String description;

    @JsonIgnore
    @Transient
    private List<Role> roles;

    @JsonIgnore
    @Transient
    private String[] permissions;

    public String getCryptPassword() {

        if(StrUtil.isBlank(cryptPassword) && StrUtil.isNotBlank(getPassword())) {
            return AESUtil.aesEncrypt(getPassword());
        }
        return cryptPassword;
    }

    public void setCryptPassword(String cryptPassword) {
        this.cryptPassword = cryptPassword;
    }

    public String getPassword() {

        if(StrUtil.isBlank(password) && StrUtil.isNotBlank(cryptPassword)) {
            password = AESUtil.aesDecrypt(cryptPassword);
        }
        return password;
    }

    public void setPassword(String password) {

        if(StrUtil.isBlank(password)) {
            password = "xcloud";
        }
        this.password = password;

        this.setCryptPassword(AESUtil.aesEncrypt(getPassword()));
    }
}

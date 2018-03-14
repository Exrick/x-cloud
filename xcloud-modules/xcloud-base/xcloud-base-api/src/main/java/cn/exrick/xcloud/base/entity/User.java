package cn.exrick.xcloud.base.entity;

import cn.exrick.xcloud.common.base.BaseXCloudEntity;
import cn.exrick.xcloud.common.utils.AESUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String id = UUID.randomUUID().toString().replace("-","");

    private String username;

    @JsonIgnore
    @Transient
    private String password;

    /**
     * 数据库映射存入加密密码
     */
    @Column(name="password")
    private String cryptPassword;

    private String nickName;

    private String telPhone;

    private String email;

    private String address;

    /**
     * 0女 1男 2保密
     */
    private Integer sex;

    private String avatar;

    private Integer type;

    @JsonIgnore
    @Transient
    private List<Role> roles;

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

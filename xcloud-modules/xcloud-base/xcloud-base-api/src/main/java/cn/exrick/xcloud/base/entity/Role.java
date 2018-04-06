package cn.exrick.xcloud.base.entity;

import cn.exrick.xcloud.common.base.BaseXCloudEntity;
import cn.exrick.xcloud.common.constant.CommonConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author Exrickx
 */
@Data
@Entity
@Table(name = "t_role")
public class Role extends BaseXCloudEntity{

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value = "唯一id")
    private String id = UUID.randomUUID().toString().replace("-","");

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "状态 默认1启用 -1禁用")
    private Integer status = CommonConstant.STATUS_ACTIVE;

    @ApiModelProperty(value = "描述备注")
    private String description;
}

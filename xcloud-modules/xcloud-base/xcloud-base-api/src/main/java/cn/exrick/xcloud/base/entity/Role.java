package cn.exrick.xcloud.base.entity;

import cn.exrick.xcloud.common.base.BaseXCloudEntity;
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
    private String id = UUID.randomUUID().toString().replace("-","");
}

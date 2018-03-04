package cn.exrick.xcloud.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Exrickx
 */
@Data
public class IpData implements Serializable{

    private static final long serialVersionUID = 1L;

    private String country;

    private String city;

    private String province;
}

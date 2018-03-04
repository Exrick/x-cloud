package cn.exrick.xcloud.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Exrickx
 */
@Data
public class IpResult implements Serializable{

    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private IpData ipData;
}

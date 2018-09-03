package cn.exrick.xcloud.common.exception;

import lombok.Data;

/**
 * @author Exrickx
 */
@Data
public class XCloudException extends RuntimeException {

    private String msg;

    public XCloudException(String msg){
        super(msg);
        this.msg = msg;
    }
}

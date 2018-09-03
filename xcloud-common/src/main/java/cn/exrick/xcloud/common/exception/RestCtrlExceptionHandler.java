package cn.exrick.xcloud.common.exception;


import cn.exrick.xcloud.common.utils.ResultUtil;
import cn.exrick.xcloud.common.vo.Result;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Exrickx
 */
@Slf4j
@RestControllerAdvice
public class RestCtrlExceptionHandler {

    @ExceptionHandler(XCloudException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleXCloudException(XCloudException e) {
        String errorMsg="XCloud exception";
        if (e!=null){
            errorMsg=e.getMsg();
            log.warn(e.toString());
        }
        return new ResultUtil<>().setErrorMsg(500, errorMsg);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    public Result<Object> handleException(Exception e) {
        String errorMsg="Exception";
        if (e!=null){
            errorMsg=e.getMessage();
            log.warn(e.toString());
            if(errorMsg.contains("XCloudException")) {
                errorMsg = e.getMessage();
                errorMsg = StrUtil.subAfter(errorMsg, "XCloudException:",false);
                errorMsg = StrUtil.subBefore(errorMsg, "\n",false);
            }
        }
        return new ResultUtil<>().setErrorMsg(500, errorMsg);
    }
}

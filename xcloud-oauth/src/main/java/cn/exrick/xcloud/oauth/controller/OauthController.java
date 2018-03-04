package cn.exrick.xcloud.oauth.controller;

import cn.exrick.xcloud.common.utils.GeetestLib;
import cn.exrick.xcloud.common.utils.ResultUtil;
import cn.exrick.xcloud.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author Exrickx
 */
@Controller
@Slf4j
public class OauthController {

    @RequestMapping(value = "/public/geetestInit",method = RequestMethod.GET)
    @ResponseBody
    public String geetesrInit(HttpServletRequest request){

        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key, GeetestLib.newfailback);
        String resStr = "{}";
        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>(10);
        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);
        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        resStr = gtSdk.getResponseStr();

        return resStr;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<Object> login(String username, String password,
                                String challenge, String validate, String seccode,
                                HttpServletRequest request){

        //极验验证
        GeetestLib gtSdk = new GeetestLib(GeetestLib.id, GeetestLib.key,GeetestLib.newfailback);
        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>(10);
        int gtResult = 0;
        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            log.info(String.valueOf(gtResult));
        } else {
            // gt-server非正常情况下，进行failback模式验证
            log.info("Failback: use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            log.info(String.valueOf(gtResult));
        }

        if (gtResult == 1) {
            // 极验验证成功进行登录

            return new ResultUtil<Object>().setData(null);
        }
        else {
            // 验证失败
            return new ResultUtil<Object>().setErrorMsg("验证失败");
        }
    }

}

package cn.exrick.xcloud.common.utils;

import cn.exrick.xcloud.common.vo.IpResult;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author Exrickx
 */
public class IpInfoUtil {

    private static final Logger log = LoggerFactory.getLogger(IpInfoUtil.class);

    /**
     * Mob api 免费无限制IP查询接口
     */
    private static final String GET_IP_API = "http://apicloud.mob.com/ip/query?key=appkey&ip=";

    private static final String LOCALHOST = "0:0:0:0:0:0:0:1";

    /**
     * 获取客户端IP地址
     * @param request 请求
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        if(LOCALHOST.equals(ip)){
            ip="127.0.0.1";
        }
        return ip;
    }

    /**
     * 获取IP返回地理信息
     * @param ip ip地址
     * @return
     */
    public static String getIpLocate(String ip){
        if(null != ip){
            String url = GET_IP_API + ip;
            String json= HttpUtil.get(url);
            String result="未知",successCode="success";
            try{
                IpResult ipResult=new Gson().fromJson(json, IpResult.class);
                if(successCode.equals(ipResult.getMsg())){
                    result=ipResult.getIpData().getCountry()+" "+ipResult.getIpData().getProvince()+" "+ipResult.getIpData().getCity();
                }
            }catch (Exception e){
                log.error("无效的IP");
            }
            return result;
        }
        return null;
    }
}

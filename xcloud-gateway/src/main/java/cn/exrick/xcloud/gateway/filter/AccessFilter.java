package cn.exrick.xcloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author Exrickx
 */
@Slf4j
public class AccessFilter extends ZuulFilter {

    @Autowired
    private ZuulProperties zuulProperties;

    @Override
    public String filterType() {
        //过滤器在请求指定生命周期中执行，"pre"代表在被路由之前执行
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //过滤器的执行顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //该过滤器是否需要被执行
        return true;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Object accessToken = request.getParameter("access_token");
        if(accessToken == null) {
            log.warn("AccessToken is empty!");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("AccessToken ok");
        return null;
    }
}

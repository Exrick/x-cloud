package cn.exrick.xcloud.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 网关整合Swagger文档在同一页面
 * @author Exrickx
 */
@Slf4j
@Component
@Primary
public class SwaggerDocumentationConfig implements SwaggerResourcesProvider {

    @Autowired
    private ZuulProperties zuulProperties;

    @Override
    public List<SwaggerResource> get() {

        List resources = new ArrayList<>();
        for(Map.Entry<String,ZuulProperties.ZuulRoute> entry: zuulProperties.getRoutes().entrySet()){
            String service = entry.getValue().getServiceId();
            if(service != null && entry.getValue().isStripPrefix()){
                SwaggerResource resource = new SwaggerResource();
                resource.setName(entry.getKey() + ":" + service);
                resource.setSwaggerVersion("2.0");
                resource.setLocation("/" + entry.getKey() + "/v2/api-docs");
                resources.add(resource);
            }
        }
        return resources;
    }
}

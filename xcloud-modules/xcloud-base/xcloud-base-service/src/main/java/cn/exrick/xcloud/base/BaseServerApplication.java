package cn.exrick.xcloud.base;

import cn.exrick.xcloud.common.config.RedisCachingConfig;
import cn.exrick.xcloud.common.config.Swagger2Config;
import cn.exrick.xcloud.common.exception.RestCtrlExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Exrickx
 */
@SpringCloudApplication
//可按需引入全局异常拦截、Swagger2、Redis等配置类[当然推荐配置扫描包]
@ComponentScan({"cn.exrick.xcloud.base", "cn.exrick.xcloud.common"})
//@Import({RestCtrlExceptionHandler.class, Swagger2Config.class, RedisCachingConfig.class})
//启用JPA审计
@EnableJpaAuditing
public class BaseServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseServerApplication.class, args);
    }
}

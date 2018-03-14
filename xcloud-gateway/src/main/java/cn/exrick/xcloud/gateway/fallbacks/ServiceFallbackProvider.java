package cn.exrick.xcloud.gateway.fallbacks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Exrickx
 */
@Slf4j
public class ServiceFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {

        log.error("网关路由: {} 异常: {}", getRoute(), cause.getMessage());

        return new ClientHttpResponse() {

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(("Service Unavailable").getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }

    @Override
    public ClientHttpResponse fallbackResponse() {

        return fallbackResponse(null);
    }
}

package com.zzx.humor.fallback;

import com.alibaba.fastjson.JSON;
import com.zzx.humor.result.R;
import com.zzx.humor.result.RE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO 网关熔断配置
 */
@Component
@Slf4j
public class HuFallback implements FallbackProvider {
    @Override
    public String getRoute() {
        return "*"; // 实现对所有的路由服务的熔断
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }

            @Override
            public InputStream getBody() throws IOException {
                String s = JSON.toJSONString(R.other(RE.TIMEOUT));
                log.error(s);
                return new ByteArrayInputStream(s.getBytes());
            }

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
                return "OK";
            }
            @Override
            public void close() {

            }
        };
    }
}

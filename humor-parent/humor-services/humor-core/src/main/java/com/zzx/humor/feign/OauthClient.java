package com.zzx.humor.feign;

import com.zzx.humor.constants.ServerConstant;
import com.zzx.humor.feign.fallback.OauthFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * TODO 调用 humor-oauth 服务
 */
@FeignClient(value = ServerConstant.OAUTH, fallback = OauthFallBack.class)
public interface OauthClient {

    /**
     * 获取token
     *
     * @param username
     * @param password
     * @param client_id
     * @param client_secret
     * @param grant_type
     * @param scope
     * @return
     */
    HashMap<String,String> getToken(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("client_id") String client_id, @RequestParam("client_secret") String client_secret, @RequestParam("grant_type") String grant_type, @RequestParam("scope") String scope);
}

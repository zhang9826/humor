package com.zzx.humor.feign;

import com.zzx.humor.constants.ServerConstant;
import com.zzx.humor.feign.fallback.OauthFallBack;
import com.zzx.humor.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * TODO 调用 humor-oauth 服务
 */
@FeignClient(value = ServerConstant.OAUTH, fallback = OauthFallBack.class)
public interface OauthClient {

    /**
     * 单线登陆 踢出前者
     * @param clientId
     * @param account
     */
    @GetMapping("/exitFormer")
    R exitFormer(@RequestParam("clientId") String clientId,@RequestParam("account") String account);

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
    @PostMapping("/oauth/token")
    HashMap<String,String> getToken(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("client_id") String client_id, @RequestParam("client_secret") String client_secret, @RequestParam("grant_type") String grant_type, @RequestParam("scope") String scope);

    /**
     * 清除token（注销登录）
     *
     * @return
     */
    @GetMapping("/exit")
    R logout(@RequestParam("token") String token);

    @PostMapping("/oauth/token")
    HashMap<String,String> refreshToken(@RequestParam("client_id") String client_id,@RequestParam("client_secret") String client_secret,@RequestParam("grant_type") String grant_type,@RequestParam("refresh_token") String refresh_token);
}

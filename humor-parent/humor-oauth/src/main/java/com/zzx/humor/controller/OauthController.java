package com.zzx.humor.controller;

import com.zzx.humor.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * TODO 认证中心对外 api
 */
@RestController
public class OauthController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    private TokenStore tokenStore;

    /**
     * 根据token获取登录用户主体相关信息（用户名，token，角色。。。等）
     *
     * @return
     */
    @GetMapping("/principal")
    public Principal principal(Principal principal) {
        return principal;
    }

    /**
     * 登出
     *
     * @param token
     * @return
     */
    @DeleteMapping(value = "/exit")
    public R exit(String token) {
        boolean b = consumerTokenServices.revokeToken(token);
        if (b) {
            return R.ok();
        } else {
            return R.failed();
        }
    }

    /**
     * 解析jwt token
     *
     * @param token
     * @return
     */
    @GetMapping("/decodeToken")
    public R decodeToken(String token) {
        Jwt decode = JwtHelper.decode(token);
        return R.ok(decode);
    }

    /**
     * 单线登陆 踢出前者
     *
     * @param clientId
     * @param account
     */
    @GetMapping("/exitFormer")
    public R exitFormer(String clientId, String account) {
        if (tokenStore.findTokensByClientIdAndUserName(clientId, account).size() == 1) {
            return R.ok();
        } else {
            return R.failed();
        }
    }
}

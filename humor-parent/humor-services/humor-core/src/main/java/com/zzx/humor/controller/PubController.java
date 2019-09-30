package com.zzx.humor.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzx.humor.constants.CommonConstant;
import com.zzx.humor.constants.OauthConstant;
import com.zzx.humor.feign.OauthClient;
import com.zzx.humor.oauth.HuUser;
import com.zzx.humor.oauth.OauthClientDetails;
import com.zzx.humor.result.R;
import com.zzx.humor.result.RE;
import com.zzx.humor.service.IHuUserService;
import com.zzx.humor.service.IOauthClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * TODO 登入 登出
 */
@RestController
public class PubController {

    @Autowired
    private IOauthClientDetailsService oauthClientDetailsService;

    @Autowired
    private OauthClient oauthClient;

    @Value("${security.oauth2.resource.id}")
    private String clientId;

    @Autowired
    private IHuUserService userService;

    /**
     * 登录 本身不做校验 交给 humor oauth 校验
     * @param account
     * @param password
     * @return
     */
    @PostMapping("/login")
    public R Login(String account, String password) {
        OauthClientDetails oauthClientDetails = oauthClientDetailsService.getOne(new QueryWrapper<OauthClientDetails>().eq("CLIENT_ID", clientId));
        if (oauthClientDetails == null) {
            return R.failed(OauthConstant.CLIENT_NOT_EXIST);
        }
        HashMap hashMap = oauthClient.getToken(account,password,clientId,oauthClientDetails.getClientSecret(),"password",oauthClientDetails.getScope());
        if (hashMap.size()==0){
            return R.other(RE.FAILED);
        }
        return R.ok(hashMap);
    }

    /**
     *  登出
     * @param token
     * @return
     */
    @DeleteMapping("/exit")
    public R exit(String token){
        return oauthClient.exit(token);
    }

    /**
     * 用户 注册
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestBody HuUser huUser){
        userService.register(huUser);
        return R.ok();
    }

    /**
     * 校验 用户名是否重复
     * @param account
     * @return
     */
    @GetMapping("/checkAccount")
    public  R checkAccount(String account){
        Boolean flag = userService.checkAccount(account);
        return R.ok(flag);
    }
}

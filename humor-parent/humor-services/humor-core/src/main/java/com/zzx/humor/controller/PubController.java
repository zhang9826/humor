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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PubController {

    @Autowired
    private IOauthClientDetailsService oauthClientDetailsService;

    @Autowired
    private OauthClient oauthClient;

    @Value("${security.oauth2.resource.id}")
    private String clientId;

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
        R r = oauthClient.exitFormer(clientId,account);
        if (r.getCode()!=R.ok().getCode()){
            //当前账号 有多个用户登陆 刷新token
            hashMap = oauthClient.refreshToken(clientId,oauthClientDetails.getClientSecret(),"refresh_token","");
        }
        return R.ok(hashMap);
    }
    @DeleteMapping("/exit")
    public R exit(String token){
        return R.ok();
    }
}

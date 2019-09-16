package com.zzx.humor.config.oauth;

import cn.hutool.json.JSONObject;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;


/**
 *  TODO 自定义token生成携带的信息
 */
public class HumorTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        // 给/oauth/token接口加属性 author
        JSONObject jsonObject = new JSONObject(authentication.getPrincipal());
        additionalInfo.put("author", jsonObject.get("username"));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}

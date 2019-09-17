package com.zzx.humor.service;

import com.zzx.humor.constants.OauthConstant;
import com.zzx.humor.dao.OauthClientDetailsMapper;
import com.zzx.humor.exception.HuRuntimeException;
import com.zzx.humor.oauth.OauthClientDetails;
import com.zzx.humor.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class HumorClientDetailsService implements ClientDetailsService {

    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OauthClientDetails oauthClientDetails = oauthClientDetailsMapper.selectById(clientId);
        if (oauthClientDetails == null) {
            throw new HuRuntimeException(R.failed(OauthConstant.CLIENT_NOT_EXIST));
        }
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(oauthClientDetails.getClientId()); //客户端(client)id
        clientDetails.setResourceIds(Arrays.asList(oauthClientDetails.getResourceIds().split(",")));//客户端所能访问的资源id集合
        clientDetails.setClientSecret(new BCryptPasswordEncoder().encode(oauthClientDetails.getClientSecret()));//客户端(client)的访问密匙
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(oauthClientDetails.getAuthorizedGrantTypes().split(",")));//客户端支持的grant_type授权类型
        clientDetails.setScope(Arrays.asList(oauthClientDetails.getScope().split(",")));//客户端申请的权限范围
        Integer accessTokenValidity = oauthClientDetails.getAccessTokenValidity();
        if (accessTokenValidity != null && accessTokenValidity > 0) {
            clientDetails.setAccessTokenValiditySeconds(accessTokenValidity);//设置token的有效期，不设置默认12小时
        }
        Integer refreshTokenValidity = oauthClientDetails.getRefreshTokenValidity();
        if (refreshTokenValidity != null && refreshTokenValidity > 0) {
            clientDetails.setRefreshTokenValiditySeconds(refreshTokenValidity);//设置刷新token的有效期，不设置默认30天
        }
        System.err.println("clientId是：" + clientId);
        return clientDetails;
    }
}

package com.zzx.humor.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzx.humor.mapper.OauthClientDetailsMapper;
import com.zzx.humor.exception.HuAccessDeniedHandler;
import com.zzx.humor.exception.HuAuthExceptionEntryPoint;
import com.zzx.humor.oauth.OauthClientDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.annotation.Resource;

/**
 * TODO 资源访问权限配置
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${clientId}")
    private String clientId;

    @Resource
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/principal","/exit","/decodeToken","/actuator/**").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        String resourceIds = oauthClientDetailsMapper.selectOne(
                new QueryWrapper<OauthClientDetails>()
                        .eq("CLIENT_ID", clientId)
                        .select("RESOURCE_IDS"))
                .getResourceIds();
        //设置客户端所能访问的资源id集合(默认取第一个是本服务的资源)
        resources.resourceId(resourceIds.split(",")[0]).stateless(true);
        //自定义Token异常信息,用于token校验失败返回信息
        resources.authenticationEntryPoint(new HuAuthExceptionEntryPoint())
                //授权异常处理
                .accessDeniedHandler(new HuAccessDeniedHandler());
    }
}

package com.zzx.humor.oauth;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhang
 * @since 2019-09-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("OAUTH_CLIENT_DETAILS")
public class OauthClientDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务注册ID
     */
    @TableId("CLIENT_ID")
    private String clientId;

    /**
     * 资源集合
     */
    @TableField("RESOURCE_IDS")
    private String resourceIds;

    @TableField("CLIENT_SECRET")
    private String clientSecret;

    @TableField("SCOPE")
    private String scope;

    @TableField("AUTHORIZED_GRANT_TYPES")
    private String authorizedGrantTypes;

    @TableField("WEB_SERVER_REDIRECT_URI")
    private String webServerRedirectUri;

    @TableField("AUTHORITIES")
    private String authorities;

    @TableField("ACCESS_TOKEN_VALIDITY")
    private Integer accessTokenValidity;

    @TableField("REFRESH_TOKEN_VALIDITY")
    private Integer refreshTokenValidity;

    @TableField("ADDITIONAL_INFORMATION")
    private String additionalInformation;

    @TableField("AUTOAPPROVE")
    private String autoapprove;


}

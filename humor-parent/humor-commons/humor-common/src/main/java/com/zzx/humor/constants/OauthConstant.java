package com.zzx.humor.constants;

/**
 * oauth 模块常量
 */
public interface OauthConstant {
    /**
     * jwt token 加密秘钥
     */
    String SIGNING_KEY = "humor-zzx";

    /**
     * jwt token 添加 用户名 key 值
     */
    String AUTHOR = "author";

    /**
     * clientId 不存在
     */
    String CLIENT_NOT_EXIST = "clientId not exist";

    /**
     * 用户不存在
     */
    String USER_NOT_EXIST = "user not exist";

    /**
     * 账号锁定
     */
    String ACCOUNT_DISABLED = "accounts disabled";

    /**
     * 账号未激活
     */
    String ACCOUNT_NOT_ACTIVATED = "account not activated";

}

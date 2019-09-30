package com.zzx.humor.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.humor.oauth.HuUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2019-09-19
 */
public interface IHuUserService extends IService<HuUser> {

    Boolean checkAccount(String account);

    void register(HuUser huUser);
}

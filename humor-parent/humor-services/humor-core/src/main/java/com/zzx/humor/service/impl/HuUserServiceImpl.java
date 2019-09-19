package com.zzx.humor.service.impl;

import com.zzx.humor.dao.HuUserMapper;
import com.zzx.humor.oauth.HuUser;
import com.zzx.humor.service.IHuUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2019-09-19
 */
@Service
public class HuUserServiceImpl extends ServiceImpl<HuUserMapper, HuUser> implements IHuUserService {

}

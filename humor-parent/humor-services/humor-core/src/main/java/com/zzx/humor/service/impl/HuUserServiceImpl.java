package com.zzx.humor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzx.humor.constants.CommonConstant;
import com.zzx.humor.mapper.HuUserMapper;
import com.zzx.humor.oauth.HuUser;
import com.zzx.humor.service.IHuUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2019-09-19
 */
@Service
@Transactional
public class HuUserServiceImpl extends ServiceImpl<HuUserMapper, HuUser> implements IHuUserService {

    @Resource
    private HuUserMapper huUserMapper;

    @Override
    public Boolean checkAccount(String account) {
        return huUserMapper.selectList(new QueryWrapper<HuUser>().eq("ACCOUNT", account).eq("FLAG", CommonConstant.NOT_DELETE)).size() == 0;
    }

    @Override
    public void register(HuUser huUser) {
        //密码加密
        String password = new BCryptPasswordEncoder().encode(huUser.getPassword());
        huUser.setPassword(password);
    }
}

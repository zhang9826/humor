package com.zzx.humor.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzx.humor.constants.OauthConstant;
import com.zzx.humor.dao.HuRoleMapper;
import com.zzx.humor.dao.HuUserMapper;
import com.zzx.humor.exception.HuRuntimeException;
import com.zzx.humor.oauth.HuMenu;
import com.zzx.humor.oauth.HuRole;
import com.zzx.humor.oauth.HuUser;
import com.zzx.humor.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HuUserDetailService implements UserDetailsService {

    @Autowired
    private HuUserMapper huUserMapper;

    @Autowired
    private HuRoleMapper huRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        HuUser huUser = huUserMapper.selectOne(new QueryWrapper<HuUser>().eq("ACCOUNT", account));
        if (huUser == null) {
            throw new HuRuntimeException(R.failed(OauthConstant.USER_NOT_EXIST));
        }
        switch (huUser.getStatus()) {
            case "0":
                throw new HuRuntimeException(R.failed(OauthConstant.ACCOUNT_DISABLED));
            case "2":
                throw new HuRuntimeException(R.failed(OauthConstant.ACCOUNT_NOT_ACTIVATED));
        }
        String password = new BCryptPasswordEncoder().encode(huUser.getPassword());
        //查该用户拥有的角色
        List<HuRole> huRoles = huRoleMapper.getRoleByUserId(huUser.getId());
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (HuRole huRole : huRoles) {
            //角色必须是ROLE_开头
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" +huRole.getRoleCode());
            grantedAuthorities.add(grantedAuthority);
            //获取权限
            for (HuMenu permission : huRole.getPermissions()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permission.getPermission());
                grantedAuthorities.add(authority);
            }
        }
        User user = new User(account, password, grantedAuthorities);
        System.err.println("当前登录的用户是：" + account);
        return user;
    }
}

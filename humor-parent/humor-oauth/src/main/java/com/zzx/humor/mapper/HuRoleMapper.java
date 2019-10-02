package com.zzx.humor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzx.humor.oauth.HuRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2019-09-17
 */
public interface HuRoleMapper extends BaseMapper<HuRole> {

    List<HuRole> getRoleByUserId(String id);
}

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
@TableName("HU_USER")
public class HuUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
    private String id;

    /**
     * 账号
     */
    @TableField("ACCOUNT")
    private String account;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 昵称
     */
    @TableField("NICKNAME")
    private String nickname;

    /**
     * 邮箱
     */
    @TableField("MAIL")
    private String mail;

    /**
     * 手机号
     */
    @TableField("PHONE")
    private String phone;

    /**
     * 性别 0 女 1 男
     */
    @TableField("SEX")
    private String sex;

    /**
     * 状态 0 禁用 1 正常 2 未激活
     */
    @TableField("STATUS")
    private String status;

    /**
     * 头像
     */
    @TableField("PHOTO")
    private String photo;

    /**
     * 0 删除 1 未删除
     */
    @TableField("FLAG")
    private String flag;


}

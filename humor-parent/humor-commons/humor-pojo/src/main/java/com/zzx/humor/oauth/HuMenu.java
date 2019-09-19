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
@TableName("HU_MENU")
public class HuMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("HM_ID")
    private String hmId;

    /**
     * 菜单名称
     */
    @TableField("MENU_NAME")
    private String menuName;

    /**
     * 父菜单ID
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 路径
     */
    @TableField("PATH")
    private String path;

    /**
     * 0 菜单 1 按钮
     */
    @TableField("TYPE")
    private String type;

    /**
     * 排序
     */
    @TableField("VIEW")
    private Integer view;

    /**
     * LOGO
     */
    @TableField("LOGO")
    private String logo;

    /**
     * 权限编码
     */
    @TableField("PERMISSION")
    private String permission;


}

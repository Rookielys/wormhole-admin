package com.haha.wormholeadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.haha.wormholeadmin.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lian
 * @since 2020-07-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tbl_sys_menu")
public class SysMenuEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 菜单url
     */
    private String menuUrl;

    /**
     * 菜单展示名
     */
    private String menuLabel;

    /**
     * 是否可见：1-是，2-否
     */
    private Boolean visibility;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 父id
     */
    private String parentId;

}

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
@TableName("tbl_sys_role_menu")
public class SysRoleMenuEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 菜单id
     */
    private String menuId;


}

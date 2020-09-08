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
@TableName("tbl_sys_role_permission")
public class SysRolePermissionEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 角色
     */
    private String roleCode;

    /**
     * 权限
     */
    private String permissionCode;


}

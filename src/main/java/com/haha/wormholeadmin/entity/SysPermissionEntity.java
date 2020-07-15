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
@TableName("tbl_sys_permission")
public class SysPermissionEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 权限编码
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

}

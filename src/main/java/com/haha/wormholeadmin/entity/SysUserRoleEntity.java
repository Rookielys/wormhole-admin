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
@TableName("tbl_sys_user_role")
public class SysUserRoleEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;


}

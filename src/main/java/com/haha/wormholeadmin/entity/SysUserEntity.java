package com.haha.wormholeadmin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.haha.wormholeadmin.entity.BaseEntity;
import java.time.LocalDate;
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
@TableName("tbl_sys_user")
public class SysUserEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别：1-男，0-女
     */
    private Integer gender;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 项目组id
     */
    private String teamId;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * qq号
     */
    private String qq;

    /**
     * 联系方式
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生日期
     */
    private LocalDate birthday;


}

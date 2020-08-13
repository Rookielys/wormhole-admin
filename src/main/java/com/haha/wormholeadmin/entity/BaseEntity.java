package com.haha.wormholeadmin.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    // 高版本mybatis支持1.8的时间api
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private String updateUser;

    @TableLogic
    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    private Integer deleteFlag;
}
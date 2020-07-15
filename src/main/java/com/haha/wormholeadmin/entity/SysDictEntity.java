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
@TableName("tbl_sys_dict")
public class SysDictEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

}

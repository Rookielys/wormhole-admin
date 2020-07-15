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
@TableName("tbl_sys_dict_item")
public class SysDictItemEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 字典id
     */
    private String dictId;

    /**
     * 字典项值
     */
    private String dictItemValue;

    /**
     * 字典项名称
     */
    private String dictItemLabel;

}

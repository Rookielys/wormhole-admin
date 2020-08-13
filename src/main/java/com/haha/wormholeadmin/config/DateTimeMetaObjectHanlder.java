package com.haha.wormholeadmin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.time.LocalDateTime;

/**
 * 字段自动填充处理器
 * 填充创建时间和更新时间
 */
public class DateTimeMetaObjectHanlder implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 根据字段名字和类型来注入合适的值
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        Subject subject = SecurityUtils.getSubject();
        this.strictInsertFill(metaObject, "createUser", String.class, subject.getPreviousPrincipals().getPrimaryPrincipal());
        this.strictInsertFill(metaObject, "deleteFlag", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 根据字段名字和类型来注入合适的值
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        Subject subject = SecurityUtils.getSubject();
        this.strictUpdateFill(metaObject, "updateUser", String.class, subject.getPreviousPrincipals().getPrimaryPrincipal());
    }
}

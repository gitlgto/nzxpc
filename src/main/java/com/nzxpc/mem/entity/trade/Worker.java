package com.nzxpc.mem.entity.trade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nzxpc.handler.mem.core.MemIdEntityNoUpdate;
import com.nzxpc.handler.util.db.IdEntityBase;
import com.nzxpc.handler.util.validate.Display;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * worker不入库 1.缓存分落地不落地，落地则要加entity。不落地不用加，但都要继承memId
 * 操作数据库的实体，一般继承idEntity,还要加entity注解，一般不会加json注解
 * worker一般放在缓存之中，所以加上json注解，但不入库,当需要继承父类时，父类需加上MappedSuperclass注解，避免映射
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Worker extends MemIdEntityNoUpdate {
    @Display("姓名")
    private String name;

    @Display("角色")
    @NotNull(message = "不能为空")
    @Range(min = 0, max = Integer.MAX_VALUE)
    private int roleId;

    private boolean isGod;
}

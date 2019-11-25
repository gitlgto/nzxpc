package com.nzxpc.mem.entity.trade;

import com.nzxpc.handler.mem.core.MemIdEntityNoUpdate;
import com.nzxpc.handler.util.validate.Display;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

/**
 * 该实体继承memId ,是存在于缓存之中，但需要落地，所以加entity注解，而memId去除主键属性，防止落地失败
 *不落地的缓存实体，需要加JsonIgnoreProperties注解，如果不需要更新时间，一般继承noupdate。
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Transfer extends MemIdEntityNoUpdate {
    @Display("名称")
    private String name;
}

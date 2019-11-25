package com.nzxpc.mem.entity.trade;

import com.nzxpc.handler.util.db.IdEntityBase;
import com.nzxpc.handler.util.validate.Display;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

/**
 * 该实体是操作数据库，所以要加上entity，并继承idEntity
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Blog extends IdEntityBase {
    @Display("名称")
    private String name;
}

package com.nzxpc.mem.entity.trade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nzxpc.handler.mem.core.MemIdEntityNoUpdate;
import com.nzxpc.handler.util.db.IdEntityBase;
import com.nzxpc.handler.util.validate.Display;
import com.nzxpc.mem.entity.common.WorkerStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

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
    @Size(max = 40)
    @NotBlank(message = "{display}不能为空")
    @Column(unique = true)
    private String name;

    @Display("密码")
    @NotBlank(message = "{display}不能为空")
    @Size(min = 6, max = 30, message = "{display}长度必须在{min}-{max}之间")
    private String passWord;

    @Display("角色")
    @NotNull(message = "不能为空")
    @Range(min = 0, max = Integer.MAX_VALUE)
    private int roleId;

    private boolean isGod;

    //最近使用的ip段（ip前两段）
    private Set<String> recentIps = new HashSet<>();

    @Display("状态")
    private WorkerStatus status = WorkerStatus.Valid;
}

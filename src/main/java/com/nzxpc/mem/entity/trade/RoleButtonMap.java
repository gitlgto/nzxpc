package com.nzxpc.mem.entity.trade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

/**
 * role和button的关系表，即直接操作数据库实体，
 */
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RoleButtonMap {
    @Id
    private int roleId;
    @Id
    private int buttonId;

}

package com.nzxpc.mem.entity.trade;

import com.nzxpc.handler.mem.core.MemIdEntityPure;
import com.nzxpc.handler.util.validate.Display;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

/**
 * role不落地，也不为数据库实体，但需要和button映射到数据库
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role extends MemIdEntityPure {
    /**
     * 类型名称
     */
    @Display("角色名称")
    @NotBlank(message = "[ {display} ]不能为空")
    @Size(max = 50, min = 1, message = "[ {display} ]长度限制为1-50")
    private String name;

    /**
     * 上级ID
     */
    @Display("上级")
    @NotNull(message = "[ {display} ]不能为空")
    @Min(value = 1, message = "[ {display} ]不合法")
    @Max(value = Integer.MAX_VALUE, message = "[ {display} ]不合法")
    private int parentId;
}

package com.nzxpc.mem.gateway.http.dto;

import com.nzxpc.handler.util.MyBeanUtils;
import com.nzxpc.mem.entity.trade.Worker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WorkerInfo {
    private int id = 0;
    private String userName;
    private boolean admin;
    private String realName;
    private String phoneNum;
    private int roleId = 0;
    private boolean platform;
    private String note;

    public static WorkerInfo valueOf(Worker worker) {
        return MyBeanUtils.copy(worker, WorkerInfo.class);
    }
}

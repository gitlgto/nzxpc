package com.nzxpc.mem.gateway.service;

import com.nzxpc.handler.mem.core.entity.Result;
import com.nzxpc.handler.util.encrypt.Md5Util;
import com.nzxpc.mem.core.infrastructure.Cache;
import com.nzxpc.mem.entity.common.WorkerStatus;
import com.nzxpc.mem.entity.trade.Worker;
import com.nzxpc.mem.gateway.http.dto.FhWorkerLoginModelIn;
import org.springframework.stereotype.Service;

@Service
public class FhWorkerService extends BaseService {
    public Result login(FhWorkerLoginModelIn model) {
        Worker worker = Cache.getWorker(model.getUserName());
        if (worker == null) {
            return new Result(false, "用户名错误");
        }
        if (!Md5Util.verifyPassword(model.getPassWord(), worker.getPassWord())) {
            return new Result(false, "密码错误");
        }
        if (worker.getStatus() == WorkerStatus.InValid) {
            return new Result(false, "用户已被封停，无法登录");
        }
        return new Result(true, "成功");
    }
}

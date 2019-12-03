package com.nzxpc.mem.gateway.http.controller;

import com.nzxpc.handler.mem.core.entity.Result;
import com.nzxpc.handler.web.ButtonAttribute;
import com.nzxpc.handler.web.ControllerInterface;
import com.nzxpc.mem.core.infrastructure.AuthUtil;
import com.nzxpc.mem.core.infrastructure.Cache;
import com.nzxpc.mem.entity.trade.Worker;
import com.nzxpc.mem.gateway.http.dto.FhHomeSettingModelOut;
import com.nzxpc.mem.gateway.http.dto.FhWorkerLoginModelIn;
import com.nzxpc.mem.gateway.http.dto.FhWorkerLoginModelOut;
import com.nzxpc.mem.gateway.http.dto.MenuItems;
import com.nzxpc.mem.gateway.service.FhWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/fhHome")
public class FhHomeController extends BaseController {

    @Autowired
    private FhWorkerService service;

    /**
     * 过滤出只是菜单以及下级 对于返回的list是否可以加注解，另外实现接口得处理
     *
     * @return
     */
    @PostMapping("setting")
    @ButtonAttribute(name = "主页")
    public FhHomeSettingModelOut setting() {
        FhHomeSettingModelOut model = new FhHomeSettingModelOut();
        //根据登录成功的getcurrentuser，周期函数
        List<MenuItems> menus = AuthUtil.getMenus(1);
        model.setMenus(menus);
        return model;

    }

    /**
     * 加权限的原因还有适用于拦截，像这种后台，接口分对worker和所有人权限
     */
    @PostMapping("login")
    @ButtonAttribute(name = "登录", parentButtonCode = "fhHome", isFreeForVisitor = true, isFreeForWorker = true)
    public Result login(@Valid FhWorkerLoginModelIn model) {
        Result result = service.login(model);
        if (!result.isOk()) {
            return result;
        } else {
            Worker worker = Cache.getWorker(model.getUserName());
            putCurrentWorker(worker);
            return result;
        }
    }

}

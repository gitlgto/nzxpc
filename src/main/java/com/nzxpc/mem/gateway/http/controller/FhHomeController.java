package com.nzxpc.mem.gateway.http.controller;

import com.nzxpc.handler.web.ButtonAttribute;
import com.nzxpc.handler.web.ControllerInterface;
import com.nzxpc.mem.core.infrastructure.AuthUtil;
import com.nzxpc.mem.gateway.http.dto.FhHomeSettingModelOut;
import com.nzxpc.mem.gateway.http.dto.MenuItems;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/fhHome")
public class FhHomeController extends BaseController {
    /**
     * 过滤出只是菜单以及下级 对于返回的list是否可以加注解，另外实现接口得处理
     * @return
     */
    @PostMapping("setting")
    @ButtonAttribute(name = "主页")
    public FhHomeSettingModelOut setting() {
        FhHomeSettingModelOut model = new FhHomeSettingModelOut();
        List<MenuItems> menus = AuthUtil.getMenus(1);
        model.setMenus(menus);
        return model;

    }

}

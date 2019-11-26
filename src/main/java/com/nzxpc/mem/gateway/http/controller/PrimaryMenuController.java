package com.nzxpc.mem.gateway.http.controller;

import com.nzxpc.handler.web.ButtonAttribute;
import com.nzxpc.handler.web.ControllerInterface;

public class PrimaryMenuController implements ControllerInterface {
    @ButtonAttribute(name = "基础数据", orderNum = 14, icon = "puzzle-piece", isMenu = true, isOnlyForSystem = true)
    public void button() {
    }

    @ButtonAttribute(name = "我的账户", orderNum = 13, icon = "male", isMenu = true, isFreeForWorker = true)
    public void account() {
    }

    @ButtonAttribute(name = "我的资产", orderNum = 12.8, icon = "money", isMenu = true)
    public void money() {
    }

    @ButtonAttribute(name = "工单管理", orderNum = 12.5, icon = "bell", isMenu = true)
    public void workOrder() {
    }

    @ButtonAttribute(name = "代理商管理", orderNum = 12, icon = "user", isMenu = true)
    public void agent() {
    }

    @ButtonAttribute(name = "商户管理", orderNum = 9.9, icon = "user", isMenu = true)
    public void merchant() {
    }

    @ButtonAttribute(name = "承兑商管理", orderNum = 9.5, icon = "user", isMenu = true)
    public void acceptor() {
    }

    @ButtonAttribute(name = "客户管理", orderNum = 9, icon = "user", isMenu = true)
    public void customer() {
    }

    @ButtonAttribute(name = "管理员管理", orderNum = 7, icon = "group", isMenu = true)
    public void worker() {
    }

    @ButtonAttribute(name = "财务管理", orderNum = 6, icon = "dollar", isMenu = true)
    public void moneyManage() {
    }

    @ButtonAttribute(name = "C2C管理", orderNum = 5.1, icon = "handshake-o", isMenu = true)
    public void c2c() {
    }

    @ButtonAttribute(name = "币订单管理", orderNum = 5, icon = "bars", isMenu = true)
    public void order() {
    }

    @ButtonAttribute(name = "钱包管理", orderNum = 4, icon = "bitcoin", isMenu = true)
    public void wallet() {
    }

    @ButtonAttribute(name = "系统设置", orderNum = 1.3, icon = "cog", isMenu = true)
    public void system() {
    }

    @ButtonAttribute(name = "文章管理", orderNum = 1.22, icon = "cog", isMenu = true)
    public void help() {
    }

    @ButtonAttribute(name = "对接管理", orderNum = 1, icon = "wrench", isMenu = true)
    public void tech() {
    }

    @ButtonAttribute(name = "后台用户基本功能", isFreeForWorker = true)
    public void home() {
    }
}

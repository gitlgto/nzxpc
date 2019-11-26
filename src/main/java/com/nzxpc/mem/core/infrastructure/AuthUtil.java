package com.nzxpc.mem.core.infrastructure;

import com.nzxpc.handler.web.entity.Button;
import com.nzxpc.mem.entity.trade.Worker;
import com.nzxpc.mem.gateway.http.dto.MenuItems;
import com.nzxpc.mem.gateway.http.dto.RoleAuthModel;

import java.util.*;

public class AuthUtil {

    public static List<MenuItems> getMenus(int workerId) {
        Worker worker = Cache.WorkerMap.get(workerId);
        // user不存在或者已禁用或者未审核通过
        if (worker == null) {
            return null;
        }

        // 1. 过滤出角色拥有权限的菜单,并放到Map中
        RoleAuthModel roleAuth = Cache.RoleAuthMap.computeIfAbsent(worker.getRoleId(), k -> new RoleAuthModel());
//        Set<Integer> excluded = Cache.Instance.WorkerExcludedButtonMap.getOrDefault(workerId, new HashSet<>());
        //|| excluded.contains(btn.getId())
        Map<String, MenuItems> btm = new HashMap<>();
        for (Button btn : Cache.CodeButtonMap.values()) {
            //过滤出只是为菜单的以及下级
            if (!btn.isMenu() || btn.isDeleted()) {
                continue;
            }
            if (worker.isGod() || ((btn.isFreeForVisitor() || btn.isFreeForWorker())) || (roleAuth.ButtonSet.contains(btn))) {
                btm.put(btn.getCode(), MenuItems.valueOf(btn));
            }
        }

        // 2. 组合成树状结构
        List<MenuItems> list = new ArrayList<>();
        btm.values().forEach(menu -> {
            HashSet<String> parentCodeSet = menu.getParentCodeSet();
            if (!parentCodeSet.isEmpty()) {
                parentCodeSet.forEach(pcode -> {
                    MenuItems menuItems = btm.get(pcode);
                    if (menuItems != null) {
                        menuItems.putChild(menu);
                    }
                });
            } else {
                list.add(menu);
            }
        });

        // 3. 排序
        sortMenuItems(list);

        return list;
    }

    /**
     * 给菜单排序
     */
    private static void sortMenuItems(List<MenuItems> list) {
        list.sort((MenuItems a, MenuItems b) -> {
            if (!Objects.equals(a.getOrderNum(), b.getOrderNum())) {
                return b.getOrderNum() > a.getOrderNum() ? 1 : -1;
            }
            return a.getId() - b.getId();
        });
        list.forEach(menu -> {
            List<MenuItems> items = menu.getItems();
            if (items != null && items.size() > 1) {
                sortMenuItems(items);
            }
        });
    }
}

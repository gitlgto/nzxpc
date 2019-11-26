package com.nzxpc.mem.core.infrastructure;

import com.nzxpc.handler.util.db.DbUtil;
import com.nzxpc.handler.util.db.IdEntityBasePure;
import com.nzxpc.handler.util.db.SqlHelper;
import com.nzxpc.handler.web.ButtonUtil;
import com.nzxpc.handler.web.entity.Button;
import com.nzxpc.mem.Application;
import com.nzxpc.mem.entity.trade.Role;
import com.nzxpc.mem.entity.trade.RoleButtonMap;
import com.nzxpc.mem.entity.trade.Worker;
import com.nzxpc.mem.gateway.http.dto.RoleAuthModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Cache {

    public static void init() {
        ButtonUtil.init(Application.class);
        loadButtonMap();
        initRootRole();
        initRootWorker();
        loadRoleAuthMap();
    }

    public static Cache Instance = new Cache();


    public static ConcurrentHashMap<Integer, Role> RoleMap = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Integer, Worker> WorkerMap = new ConcurrentHashMap<>();
    //key：uri,“/controller/action”
    public static ConcurrentHashMap<String, Button> UriButtonMap = new ConcurrentHashMap<>();
    //key：code,“controller_action”
    public static ConcurrentHashMap<String, Button> CodeButtonMap = new ConcurrentHashMap<>();
    //key:role id,封装了一个Role关联的Button
    public static ConcurrentHashMap<Integer, RoleAuthModel> RoleAuthMap = new ConcurrentHashMap<>();
    // 权限排除
    public ConcurrentHashMap<Integer, Set<Integer>> WorkerExcludedButtonMap = new ConcurrentHashMap<>();

    private static void loadButtonMap() {
        SqlHelper<Button> helper = DbUtil.getSqlHelper(Button.class);
        List<Button> list = helper.list();
        Assert.isTrue(list.size() > 0, "db中尚未初始化button");
        UriButtonMap.clear();
        CodeButtonMap.clear();
        list.forEach(btn -> {
            if (StringUtils.isNotBlank(btn.getUri())) {
                UriButtonMap.put(btn.getUri(), btn);
            }
            if (StringUtils.isNotBlank(btn.getCode())) {
                CodeButtonMap.put(btn.getCode(), btn);
            }
        });
    }

    private static void loadRoleAuthMap(int roleId, List<RoleButtonMap> maps) {
        Map<Integer, Button> idBtnMap = CodeButtonMap.values().stream().collect(Collectors.toMap(IdEntityBasePure::getId, a -> a));
        if (roleId == 0) {
            RoleMap.keySet().forEach(rId -> {
                RoleAuthMap.put(rId, new RoleAuthModel());
            });
        } else {
            RoleAuthMap.put(roleId, new RoleAuthModel());
        }
        maps.forEach(m -> {
            RoleAuthModel model = RoleAuthMap.get(m.getRoleId());
            if (model != null) {
                Button btn = idBtnMap.get(m.getButtonId());
                if (btn != null) {
                    model.ButtonSet.add(btn);
                    if (StringUtils.isNotBlank(btn.getUri())) {
                        model.UriSet.add(btn.getUri());
                    }
                }
            }
        });
    }

    // 初始化账户并分配权限
    private static void initRootRole() {
        Role role = new Role();
        role.setName("").setParentId(-1);
        RoleMap.put(0, role);
    }

    private static void initRootWorker() {
        Worker worker = new Worker();
        worker.setGod(true);
        worker.setName("000");
        worker.setRoleId(0);
        worker.setId(1);
        WorkerMap.put(worker.getId(), worker);
    }

    private static void loadRoleAuthMap() {
        SqlHelper<RoleButtonMap> helper = DbUtil.getSqlHelper(RoleButtonMap.class);
        RoleAuthMap.clear();
        loadRoleAuthMap(0, helper.list().stream().filter(a -> RoleMap.keySet().contains(a.getRoleId())).collect(Collectors.toList()));
    }

}

package com.nzxpc.mem.gateway.http.dto;

import com.nzxpc.handler.web.entity.Button;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RoleAuthModel {
    //value.Set中的元素是：“/controller/action”，不含Menu
    public Set<String> UriSet = ConcurrentHashMap.newKeySet();

    //含Menu
    public Set<Button> ButtonSet = ConcurrentHashMap.newKeySet();
}

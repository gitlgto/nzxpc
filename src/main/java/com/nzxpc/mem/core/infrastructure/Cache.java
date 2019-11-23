package com.nzxpc.mem.core.infrastructure;

import com.nzxpc.handler.web.entity.Button;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    public static Cache Instance = new Cache();
    public ConcurrentHashMap<String, Button> ButtonMap = new ConcurrentHashMap<>();
}

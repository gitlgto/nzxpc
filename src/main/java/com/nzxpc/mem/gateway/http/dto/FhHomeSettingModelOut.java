package com.nzxpc.mem.gateway.http.dto;

import com.nzxpc.handler.mem.core.entity.Result;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class FhHomeSettingModelOut extends Result {
    List<MenuItems> menus;
    private WorkerInfo data;
}

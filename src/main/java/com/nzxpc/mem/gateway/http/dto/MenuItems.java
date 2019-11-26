package com.nzxpc.mem.gateway.http.dto;

import com.nzxpc.handler.web.entity.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class MenuItems implements Serializable {
    private int id = 0;

    private String code;

    private HashSet<String> parentCodeSet = new HashSet<>();

    private String title; // 标题

    private String icon; // 图标

    private String uri; // url 如果存在的话

    private String sref; // app.#{controller}.#{action} anjularjs的controller同名

    private double orderNum = 0;

    private List<MenuItems> items = new ArrayList<>();

    public static List<MenuItems> valueOf(List<Button> buttonList) {
        List<MenuItems> menuitem = new ArrayList<>();
        for (Button button : buttonList) {
            menuitem.add(MenuItems.valueOf(button));
        }
        return menuitem;
    }

    public static MenuItems valueOf(Button button) {
        MenuItems item = new MenuItems();
        item.setId(button.getId());
        item.setCode(button.getCode());
        item.setTitle(button.getName());
        if (StringUtils.isNotBlank(button.getImgClass())) {
            item.setIcon(button.getImgClass());
        }
        if (StringUtils.isNotBlank(button.getUri())) {
            String params = "";
            if (StringUtils.isNotBlank(button.getDefaultParams())) {
                params = "?" + StringEscapeUtils.unescapeHtml(button.getDefaultParams());
            }
            item.setUri(button.getUri() + params);
        }
        item.setOrderNum(button.getOrderNum());
        if (StringUtils.isNotBlank(button.getParentCode())) {
            String[] split = button.getParentCode().split(",");
            item.getParentCodeSet().addAll(Arrays.asList(split));
        }
        return item;
    }

    public void putChild(MenuItems child) {
        if (this.items == null) {
            this.items = new ArrayList<MenuItems>();
        }
        if (!this.items.contains(child)) {
            this.items.add(child);
        }
    }
}

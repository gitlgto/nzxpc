package com.nzxpc.tg.manage.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/home")
public class HomeController {
    @RequestMapping("login")
    public model login() {
        model model=new model();

        List<Integer>list=new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        model.setList(list);
        return model;
    }
}

package com.nzxpc.mem.gateway.http.dto;

public class FhWorkerModel {
    //TODO 1.输入model继承带泛型的pagebean，返回model（带泛型）继承result，注解无参构造，忽略字段，字段pagebean（对应泛型返回model中的泛型），
    // 有参构造（ok，pagebean）data（普通model）（查数据库，直接用传入model创建查询，返回model设置data（传入的model）返回）（查缓存同理）
    //TODO 2.登录成功后，输入model继承带分页数据的model，返回model继承带泛型的pagebean，缓存（返回model设置输入model带过来的分页参数，然后进行查询，
    // 为什么用返回model查询，因为可以直接返回）数据库（创建查询工具类和带泛型的pagebean，pagebean设置分页参数，传入pagebean进行查询，返回model设置查询后数据返回
    // 报错是因为创建的查询工具类的类型要是返回数据的类型，传入的model继承的pagebean的类型对应前者类型，不过一般还是查询实体，然后转化下）
    //3.先使用第一种
}

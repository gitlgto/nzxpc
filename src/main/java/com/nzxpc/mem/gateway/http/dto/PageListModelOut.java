package com.nzxpc.mem.gateway.http.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nzxpc.handler.mem.core.entity.Result;
import com.nzxpc.handler.util.db.PageBean;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageListModelOut<T> extends Result {
    @Getter
    @Setter
    private PageBean<T> pageBean;

    public PageListModelOut(boolean ok, PageBean<T> pageBean) {
        //从子类调用父类的构造方法，默认会有一个super()，无参的，所以在创建子类对象时，父类构造会先执行，这样父类就会设置了值，子类使用时也会有值
        // 调用父类的构造函数设置ok的值，这边继承也是有值 如果父类没有无参构造，则子类必须显示的调用父类有参构造
        // 因为是继承父类的ok，所以只能手动写有参构造，指向父类的ok。
        super(ok);
        this.pageBean = pageBean;
    }
}

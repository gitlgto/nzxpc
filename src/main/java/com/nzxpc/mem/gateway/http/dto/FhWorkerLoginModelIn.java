package com.nzxpc.mem.gateway.http.dto;

import com.nzxpc.handler.util.validate.Display;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Accessors(chain = true)
public class FhWorkerLoginModelIn {
    // 全局异常参数验证数据统一处理并返回，状态码是200，而不再是400

    @Display("用户名")
    @NotBlank(message = "[ 用户名 ]必填")
    @Size(max = 40, message = "[ 用户名 ]长度不能超过{max}")
    private String userName;

    @Display("密码")
    @NotBlank(message = "[ 密码 ]必填")
    @Size(min = 6, max = 30, message = "密码长度必须在{min}-{max}之间")
    private String passWord;
}

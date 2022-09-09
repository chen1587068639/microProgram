package com.chen.common.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Data
public class TestVo {

    /**
     * NotNull:, Collection, Map 和 Array 对象不能是 null, 但可以是空集size = 0
     * NotEmpty:, Collection, Map 和 Array 对象不能是 null 并且相关对象的 size 大于 0。
     * NotBlank:String 不是 null 且去除两端空白字符后的长度（trimmed length）大于 0。
     */
    @NotNull
    private Integer num;

    @NotBlank
    private String name;

    @NotBlank
    private String address;
}

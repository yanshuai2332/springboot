package com.yan.base.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yanshuai on 2017/8/4.
 */
@Data
@NoArgsConstructor
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;
}

package com.yan.base.repository;

import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * Created by yanshuai on 2017/8/3.
 */
public interface BaseMapper<T> extends tk.mybatis.mapper.common.BaseMapper<T>,
        SelectByIdsMapper<T> {
}

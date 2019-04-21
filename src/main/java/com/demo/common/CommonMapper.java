package com.demo.common;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;
public interface CommonMapper<T> extends Mapper<T>, IdListMapper<T,Long>,InsertListMapper<T> {
}

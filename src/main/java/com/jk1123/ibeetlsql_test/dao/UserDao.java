package com.jk1123.ibeetlsql_test.dao;

import com.jk1123.ibeetlsql_test.pojo.User;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
@SqlResource("user")
public interface UserDao extends BaseMapper<User> {
}

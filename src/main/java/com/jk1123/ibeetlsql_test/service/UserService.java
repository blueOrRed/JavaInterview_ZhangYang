package com.jk1123.ibeetlsql_test.service;

import com.jk1123.ibeetlsql_test.pojo.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserService {
    //分页查询
    public List<User> query(User user);
    //根据id删除
    public void deleteById(long id);
    //增加或者修改
    public void addAndUpdate(User user);

    //导出数据
    public List<User> selectUser();
}

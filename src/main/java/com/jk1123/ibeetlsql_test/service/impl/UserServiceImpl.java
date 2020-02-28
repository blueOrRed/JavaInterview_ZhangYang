package com.jk1123.ibeetlsql_test.service.impl;

import com.jk1123.ibeetlsql_test.dao.UserDao;
import com.jk1123.ibeetlsql_test.pojo.User;
import com.jk1123.ibeetlsql_test.service.UserService;
import com.jk1123.ibeetlsql_test.utils.IdWorker;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SQLManager sqlManager;
    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public List<User> query(User user) {
        int spPage = 0;
        //设置每页条数
        int pageSize=user.getPageSize();

        if(user.getTempPageNo()==null){
            spPage=1;
        }else {
            spPage = user.getTempPageNo();
            if (user.getTempPageNo() < 1) {
                spPage = 1;
            }
        }
        //计算当前从哪里开始查询
        int tempPageNo=(spPage-1)*pageSize;
        user.setTempPageNo(tempPageNo);

        List<User> userLoginList  = sqlManager.select("user.selectAll", User.class,user);
        return userLoginList;
    }

    @Override
    public void deleteById(long id) {
        sqlManager.deleteById(User.class,id);
    }
    @Override
    public void addAndUpdate(User user) {


        long id = user.getId();
        if(id==0){
            long nextId = idWorker.nextId();
            user.setId(nextId);
            userDao.insert(user);
        }else {
            //执行修改
            userDao.updateById(user);
        }
    }

    @Override
    public List<User> selectUser() {
        List<User> userList = sqlManager.select("user.queryAllUser", User.class);
        return userList;
    }
}

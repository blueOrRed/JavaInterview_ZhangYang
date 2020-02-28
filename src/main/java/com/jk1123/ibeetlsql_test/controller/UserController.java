package com.jk1123.ibeetlsql_test.controller;

import com.jk1123.ibeetlsql_test.pojo.User;
import com.jk1123.ibeetlsql_test.result.Result;
import com.jk1123.ibeetlsql_test.result.StatusCode;
import com.jk1123.ibeetlsql_test.service.UserService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private  HttpServletResponse response;
    //分页
    @RequestMapping("/queryUser")
    public Result queryUser(@RequestBody User user){

        try {
            List<User> userList = userService.query(user);
            return new Result(true, StatusCode.OK,"查询成功",userList);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(true, StatusCode.OK,"查询失败");

        }
    }
    //根据id删除
    @RequestMapping("/{id}")
    public Result deleteById(@PathVariable long id){

        try {
             userService.deleteById(id);
            return new Result(true, StatusCode.OK,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(true, StatusCode.OK,"删除成功");
        }
    }
    //添加或删除
    @RequestMapping("/addAndUpdate")
    public Result addAndUpdate(@RequestBody User user){

        try {
            userService.addAndUpdate(user);
            return new Result(true, StatusCode.OK,"成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(true, StatusCode.OK,"失败");
        }
    }
    //导出数据到excel
    @RequestMapping("/report")
    public Result report(){

        try {
            List<User> userList = userService.selectUser();
            InputStream inputStream = this.getClass().getResourceAsStream("/templates/UserTop.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            for (int i = 0; i < userList.size(); i++) {
                XSSFSheet sheetAt = workbook.getSheetAt(1);
                User user = userList.get(i);
                XSSFRow row = sheetAt.getRow(i + 1);
                XSSFCell cell = row.getCell(i);
                cell.setCellValue(user.getId());

                cell = row.getCell(i + 1);
                cell.setCellValue(user.getUsername());

                cell = row.getCell(i + 2);
                cell.setCellValue(user.getPassword());

            }
            // 通过输出流进行文件下载
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            Date date = new Date();
            response.setHeader("content-Disposition","attachment;filename="+date+"_report.xlsx");
            workbook.write(out);
            out.flush();
            out.close();
            workbook.close();

            return new Result(true, StatusCode.OK,"成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(true, StatusCode.OK,"失败");
        }
    }
}

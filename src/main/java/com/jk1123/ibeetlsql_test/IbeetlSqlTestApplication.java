package com.jk1123.ibeetlsql_test;

import com.jk1123.ibeetlsql_test.utils.IdWorker;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class IbeetlSqlTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IbeetlSqlTestApplication.class, args);
    }


    @Bean
    public IdWorker getIdWorekr(){
        return new IdWorker();
    }

}

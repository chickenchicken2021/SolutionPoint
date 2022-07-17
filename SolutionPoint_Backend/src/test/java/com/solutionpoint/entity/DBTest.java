package com.solutionpoint.entity;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {

    @Test
    public void DBTest(){
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/solution?serverTimezone=UTC", "solution", "solution1234")) {
            System.out.println(conn);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

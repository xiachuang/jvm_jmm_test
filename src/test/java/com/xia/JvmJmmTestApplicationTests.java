package com.xia;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JvmJmmTestApplicationTests {
    public void changeAge(int age){
        age=100;
    }
    public void changName(String name){
        name="xxx";
    }
    @Test
    void contextLoads() {

    int age=20;

    }

}

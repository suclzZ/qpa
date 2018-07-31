package com.app;

import com.app.qpa.Application;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= Application.class)
public class AppTest {

    public static void main(String[] args) {

        System.out.println( " : " + NumberUtils.isNumber("01"));
    }

}

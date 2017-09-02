package com.tubugs.springboot.test.helper;

import com.tubugs.springboot.helper.OrderNoHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xuzhang on 2017/8/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderNoTest {

    @Autowired
    private OrderNoHelper orderNoHelper;

    @Test
    public void generate() {
        System.out.println(orderNoHelper.generate());
    }
}

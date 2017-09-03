package com.tubugs.springboot.test.mybatis;

import com.github.pagehelper.PageHelper;
import com.tubugs.springboot.dao.entity.Role;
import com.tubugs.springboot.dao.mapper.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by xuzhang on 2017/9/3.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatiasTest {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void test() {
        PageHelper.startPage(1, 2); // 核心分页代码
        List<Role> cls = roleMapper.selectAll();
        System.out.println(cls.size());
    }

}

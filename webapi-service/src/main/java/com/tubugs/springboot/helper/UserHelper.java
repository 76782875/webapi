package com.tubugs.springboot.helper;

import com.tubugs.springboot.dao.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzhang on 2017/9/3.
 */
public class UserHelper {
    public static List<Long> getNos(List<User> users) {
        if (users == null || users.size() == 0)
            return null;
        List<Long> nos = new ArrayList<Long>();
        for (User user : users) {
            nos.add(user.getNo());
        }
        return nos;
    }
}

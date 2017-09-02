package com.tubugs.springboot.ability.push;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by xuzhang on 2017/9/1.
 */
public interface PushAbility {

    /**
     * 发送消息
     *
     * @param account
     * @param title
     * @param body
     * @param json
     * @return
     */
    boolean sendMessage(String account, String title, String body, JSONObject json);

    /**
     * 发送通知
     *
     * @param account
     * @param title
     * @param body
     * @param json
     * @return
     */
    boolean sendNotice(String account, String title, String body, JSONObject json);

}

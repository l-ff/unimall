package com.iotechn.unimall.biz.mq.listener;

import com.iotechn.unimall.biz.mq.handler.RedisNotifyHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.HashMap;

public class RedisExpiredListener implements MessageListener {

    /**
     * 客户端监听订阅的topic，当有消息的时候，会触发该方法;
     * 并不能得到value, 只能得到key。
     * 姑且理解为: redis服务在key失效时(或失效后)通知到java服务某个key失效了, 那么在java中不可能得到这个redis-key对应的redis-value。
     */
    @Autowired
    private HashMap<Integer, RedisNotifyHandler> handlerRouter;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String expiredKey = message.toString();
        // TASK:UUID:CODE:VALUE结构
        String[] split = expiredKey.split(":");
        if (split.length < 3 || !expiredKey.startsWith("TASK:")) {
            return;
        }
        StringBuilder value = new StringBuilder();
        for (int i = 3; i < split.length; i++) {
            value.append(split[i]);
            if (i != split.length - 1) {
                value.append(":");
            }
        }
        int code = Integer.parseInt(split[2]);
        RedisNotifyHandler handler = handlerRouter.get(code);
        if (handler != null) {
            handler.handle(value.toString());
        }
    }
}
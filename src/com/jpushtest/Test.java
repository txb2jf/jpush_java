package com.jpushtest;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class Test {
    protected static final Logger LOG = LoggerFactory.getLogger(Test.class);
    public static final String ALERT = "数据";

    public static void main(String[] args) {
        testSendPush();
    }

    public static void testSendPush() {
        JPushClient jpushClient = new JPushClient(Config.masterSecret, Config.appKey);
        PushPayload payload = buildPushObject_all_all_alert();

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }

    public static PushPayload buildPushObject_all_all_alert() {
//        PushPayload payload = PushPayload
//                .newBuilder()
//                .setPlatform(Platform.android())
//                .setAudience(Audience.tag("tag1"))
//                .setNotification(
//                        Notification
//                                .newBuilder()
//                                .setAlert(ALERT)
//                                .addPlatformNotification(
//                                        IosNotification.newBuilder().incrBadge(1)
//                                                .addExtra("extra_key", "extra_value").build())
//                                .build()).build();
//        return payload;
        Map<String, String> extras = new HashMap<String, String>();
        extras.put("Notificaction", "0");
        extras.put("URL", "www.baidu.com");

        Message.content("数据");
        PushPayload payload = PushPayload.newBuilder()
                   .setPlatform(Platform.android())
                   .setAudience(Audience.alias("0123456789"))
                   .setOptions(Options.sendno())
                   .setNotification(Notification.android("一个惊喜", "号外", extras))
                   //.setMessage(Message.newBuilder().setMsgContent("").addExtra("Notificaction", "0").build())
//                   .setMessage(
//                        Message.newBuilder().setMsgContent("数据").addExtra("Notificaction", "7").setTitle("大爷的")
//                                .build())
                   .build();
                   
                   
        return payload;
        
    }

}

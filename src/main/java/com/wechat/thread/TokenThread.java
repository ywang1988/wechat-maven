package com.wechat.thread;

import com.wechat.bean.AccessToken;
import com.wechat.util.AccessTokenUtil;

/**
 * Created by wang_ on 2016-08-22.
 */
public class TokenThread implements Runnable {
    // 微信公众号的凭证和秘钥（自己公账号的信息）
    public static final String appID = "";
    public static final String appScret = "";
    public static AccessToken access_token = null;

    @Override
    public void run() {
        while (true) {
            try {
                //调用工具类获取access_token(每日最多获取100000次，每次获取的有效期为7200秒)
                if (appID == null || "".equals(appID)) {
                    throw new IllegalArgumentException("微信公众号的凭证为空！");
                }
                if (appScret == null || "".equals(appScret)) {
                    throw new IllegalArgumentException("微信公众号的秘钥为空！");
                }
                access_token = AccessTokenUtil.getAccessToken(appID, appScret);
                if (null != access_token) {
                    System.out.println("accessToken获取成功：" + access_token.getExpires_in());
                    System.out.println("accessToken获取成功：" + access_token.getAccess_token());
                    //7000秒之后重新进行获取
                    Thread.sleep((access_token.getExpires_in() - 200) * 1000);
                } else {
                    //获取失败时，60秒之后尝试重新获取
                    Thread.sleep(60 * 1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

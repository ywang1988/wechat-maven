package com.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by wang_ on 2016-08-21.
 */
public class WeChatUtil {
    private static final String token = ""; // 自己的token

    /**
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature (String signature, String timestamp, String nonce) {
        if (token == null || "".equals(token)) {
            throw new IllegalArgumentException("token 为空！");
        }
        String[] array = new String[]{token, timestamp, nonce};

        // 1 排序
        Arrays.sort(array);

        // 2 拼接字符串
        StringBuffer content = new StringBuffer();
        for (String s : array) {
            content.append(s);
        }

        // 3 加密
        String sha1 = getSHA1(content.toString());
        return signature.equals(sha1);
    }

    /**
     *
     * @param decript
     * @return
     */
    public static String getSHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}

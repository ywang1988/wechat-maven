package com.wechat.util;

/**
 * Created by wang_ on 2016-07-27.
 */
public final class EncryptUtil {

    /**
     * 加密
     * @param value 加密字符串
     * @return
     * @throws Exception
     */
    public static String encrypt(String value) throws Exception {
        return ByteUtils.toHEX(AESEncrypt.getInstance().encrypt(value.getBytes())).toUpperCase();
    }

    /**
     * 解密
     * @param value 解密字符串
     * @return
     * @throws Exception
     */
    public static String decrypt(String value) throws Exception {
        return new String(AESEncrypt.getInstance().decrypt(ByteUtils.string2ByteArray(value)));
    }
}

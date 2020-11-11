package com.wzwl.authentication.utils;


import org.apache.commons.codec.digest.DigestUtils;


/**
 * @author huff
 * @date 2020/11/10 16:46
 */
public class Md5Util {

    private static final String salt = "wzwl2020";

    public static String encryPassword(String password){

        return DigestUtils.md5Hex(password+salt);
    }


}

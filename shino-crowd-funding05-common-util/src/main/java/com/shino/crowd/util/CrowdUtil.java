package com.shino.crowd.util;

import com.shino.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * @author QinE
 * @create 2022-10-18 18:27
 */
public class CrowdUtil {

    /**
     * 判断当前请求是否为Ajax请求
     * @param request
     * @return
     */
    public static boolean judgeRequestType(HttpServletRequest request) {

        //1.获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String XRequestHeader = request.getHeader("X-Requested-With");

        //2.判断
        return (acceptHeader != null && acceptHeader.contains("application/json")) ||
                (XRequestHeader != null && XRequestHeader.equals("XMLHttpRequest"));
    }

    /**
     * 对明文字符串进行加密
     * @param source 传入的明文字符串
     * @return 加密结果
     */
    public static String md5(String source) {

        // 1.判断source是否有效
        if (source == null || source.length() == 0) {


            // 2.如果不是有效的字符串，抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);

        }

        try {
            // 3.获取MessageDigest对象
            String algorithm = "md5";

            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 4.获取明文字符串对的字节数组
            byte[] input = source.getBytes(StandardCharsets.UTF_8);

            // 5.执行加密
            byte[] output = messageDigest.digest(input);

            // 6.创建BigInteger对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            // 7.按照十六进制将bigInteger的值转为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}

package com.dianjiake.android.util;



import com.dianjiake.android.constant.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Sacowiw on 2017/5/17.
 */

public class AppUtil {
    public static String encryptSign(String t, String s, String p, String i) {
        return MD5(t+ p + Constant.SALT + i);
    }

    public static String MD5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

}

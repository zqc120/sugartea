package com.dianjiake.android.constant;

/**
 * Created by lfs on 2017/6/6.
 */

public interface Constant {

    String WX_AppID = "wxcdea443bd8007398";
    String WX_AppSecret = "a813f7bd20858f729683dd0672fcd984";
    String URL_VERSION = "api100/";

    String SALT = "3c66790a694fd53aed110a8e85f1aeec";
    float CHART_LINE_SIZE = 1f;

    /**
     * 测试
     */
    String ROOT = "http://www.quanminlebang.cn/";
    String APP_URL = ROOT + URL_VERSION;
    boolean IS_DEBUG = true;

    /**
     * 正式
     */
//    String ROOT = "http://www.quanminlebang.com/";
//    String APP_URL = ROOT + URL_VERSION;
//    boolean IS_DEBUG = true;

    String USER = "user.php";
    String INCOME = "yingshou.php";
    String ORDERS = "dingdan.php";
    String SERVICE = "fuwu.php";
    String MSG = "xiaoxi.php";
    String SHOP = "shanghu.php";
    String PERFORMANCE = "yeji.php";//业绩
    String IMAGE_SERVICE = ROOT + "photo/fuwu/";//服务logo
    String IMAGE_PRO_PHOTO = ROOT + "photo/zhiyezhao/";//职业照
    String IMAGE_AVATAR = ROOT + "photo/avatar/";//头像
    String IMAGE_SHOP = ROOT + "photo/shanghu/";//头像
    String IMAGE_QR = ROOT + "photo/erweima/";//二维码

    String SHOP_HOMEPAGE =ROOT+"/m/mobiles/index.html#/page/main?share=yes&id=";

    String NETWORK_INFO = "http://pv.sohu.com/cityjson/";

}

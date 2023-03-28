package com.weblab.adt.weblab.config;

/**
 * @author : yingmu on 15-3-16.
 * @email : yingmu@mogujie.com.
 * 服务器地址设置
 */
public class UrlConstant {

    // 头像路径前缀
    public final static String AVATAR_URL_PREFIX = "";

    // access 地址  http://47.97.181.98:8080
//    public final static String ACCESS_MSG_ADDRESS = "http://61.153.100.221:8080/msg_server";
//    public final static String ACCESS_MSG_ADDRESS = "http://teamtalk.p52.cn:5000/msg_server";
//    public final static String ACCESS_MSG_ADDRESS = "http://47.97.181.98:8080/msg_server"; //默认值。用户名：小赵 密码不为空就好
//    public final static String ACCESS_MSG_ADDRESS = "http://192.168.0.29:8080/msg_server"; //默认值。用户名：102 密码不为空就好
    public final static String ACCESS_MSG_ADDRESS = "http://192.168.10.105:8080/msg_server"; //默认值。用户名：102 密码不为空就好
    public final static String ACCESS_MSG_ADDRESS_END = ":8080/msg_server"; //默认值。用户名：102 密码不为空就好
//    public final static String ACCESS_MSG_ADDRESS = "http://10.5.45.63:8080/msg_server";
//    public final static String ACCESS_MSG_ADDRESS = "http://192.168.239.128:8080/msg_server";
//    public final static String ACCESS_MSG_ADDRESS = "http://im.xiaominfc.com:8080/msg_server";
//    public final static String ACCESS_MSG_ADDRESS = "http://chat.xiaominfc.com/msg_server"; //用户名test 密码 test  用户名1005密码1005
//INSERT INTO `IMUser` (`id`, `sex`, `name`, `domain`, `nick`, `password`, `salt`, `phone`, `email`, `avatar`, `departId`, `status`, `created`, `updated`, `push_shield_status`, `sign_info`) VALUES ('1', '0', '101', '127.0.0.1', '', '', '', '', '', '', '1', '1', '1', '1', '0', '')
}

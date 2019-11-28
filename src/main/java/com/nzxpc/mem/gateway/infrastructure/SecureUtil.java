package com.nzxpc.mem.gateway.infrastructure;

import com.nzxpc.handler.util.IpUtil;
import com.nzxpc.handler.util.LogUtil;
import com.nzxpc.mem.entity.trade.Worker;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SecureUtil {
    private static Pattern IPV4_PATTERN = null;
    private static final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";

    static {
        try {
            IPV4_PATTERN = Pattern.compile(ipv4Pattern, Pattern.CASE_INSENSITIVE);
        } catch (PatternSyntaxException e) {
            LogUtil.errThenExit(e);
        }
    }

    /**
     * 检测这次ip和最近ip相比，前两段是否变化
     * <p>
     * 1、对需要登录的操作做如下判断：
     * 如果发现本次IP不属性最近IP，则强制会话过期，手机验证码登录
     * </p>
     * <p>
     * 2、对登录操作做如下判断：
     * 若发现登录IP不属于最近IP，则强制手机验证码登录
     * </p>
     * <p>
     * 3、登录成功后加白(调用SecureUtil.loginSucc）
     * </p>
     */
    private static Set<String> getOrAddIps(Worker worker) {
        Set<String> ips = worker.getRecentIps();
        if (ips == null) {
            ips = new HashSet<>();
            worker.setRecentIps(ips);
        }
        return ips;
    }
    private static String adjustIp(String ip) {
        if (IPV4_PATTERN.matcher(ip).matches()) {
            var arr = StringUtils.split(ip, ".");
            return arr[0] + "." + arr[1];
        } else {
            return ip;
        }
    }
    public static boolean isDangerIp(Worker user, String thisIp) {
        if (IpUtil.isLocal(thisIp)) {
            return false;
        }
        String ip = adjustIp(thisIp);
        return !getOrAddIps(user).contains(ip);
    }
    /**
     * 登录成功后将ip加白（加到最近ip中）
     */
    public static void loginSucc(Worker user, String thisIp) {
        getOrAddIps(user).add(adjustIp(thisIp));
    }
}

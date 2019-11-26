package com.nzxpc.mem.core.infrastructure;

import com.nzxpc.handler.web.WebUtil;
import com.nzxpc.handler.web.entity.Button;
import com.nzxpc.mem.entity.trade.Worker;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * 登陆拦截器
 */
public class AuthorityInterceptor implements HandlerInterceptor {
    private final static Set<String> ACCEPT = Set.of("/", "fhHome/login", "fhHome/logout");


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session,以获取user
        HttpSession session = request.getSession();
        String uri = WebUtil.uri(request);
        if (ACCEPT.contains(uri)) {
            //永不缓存
            response.setHeader("Last-Modified", DateConverter.toGMTString(new Date()));
            return true;
        }
        if (uri.startsWith("/fh")) {
            return checkFhApAction(session, response, uri);
        }

        return false;
    }

    private boolean checkFhApAction(HttpSession session, HttpServletResponse response, String uri) throws IOException {
        boolean login = "/fhHome/login".equalsIgnoreCase(uri);
        if (login) {
            return true;
        }
        Worker worker = (Worker) session.getAttribute("user");

        Button button = Cache.CodeButtonMap.get(uri);
        if (button == null) {
            if (worker != null) {
                return WebUtil.noAuth();
            }
        } else {
            if (worker == null) {
                return WebUtil.sessionGone();
            }
        }
        return true;
    }
}

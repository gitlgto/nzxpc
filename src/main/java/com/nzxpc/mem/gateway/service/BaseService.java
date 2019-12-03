package com.nzxpc.mem.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseService {
    @Autowired
    protected HttpSession session;

    /**
     *传入request的目的还是获得ip ipUtil
     */
    @Autowired
    protected HttpServletRequest request;
}

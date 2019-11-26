package com.nzxpc.mem.gateway.http.controller;

import com.nzxpc.handler.web.ControllerInterface;
import com.nzxpc.mem.entity.trade.Worker;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public abstract class BaseController implements ControllerInterface {
    @Autowired
    protected HttpSession session;


    protected void putCurrentWorker(Worker worker) {
        session.setAttribute("worker", worker);
    }

    protected Worker getCurrentWorker() {
        return (Worker) session.getAttribute("worker");
    }
}

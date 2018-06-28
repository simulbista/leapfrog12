/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herokuapp.listener;

import com.herokuapp.dao.AccessLogDAO;
import com.herokuapp.dao.impl.AccessLogDAOImpl;
import com.herokuapp.entity.AccessLog;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author 647-np
 */
@WebListener
public class AccessListener implements ServletRequestListener {

    private AccessLogDAO accessLogDAO = new AccessLogDAOImpl();

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

//        testing listener
//        System.out.println("URI = " + request.getRequestURI());
//        System.out.println("IP Address = " + request.getRemoteAddr());
//        System.out.println("Method =  " + request.getMethod());
//        System.out.println("Access Date = " + new Date());
//        System.out.println("User Agent = " + request.getHeader("User-Agent")); 
//        Enumeration<String> keys = request.getParameterNames();
//        System.out.println("Request Data: ");
//        while(keys.hasMoreElements()){
//            String key = keys.nextElement();
//            System.out.println(key + " => " + request.getParameter(key));
//        }
        AccessLog log = new AccessLog();

        log.setUrl(request.getRequestURI());
        log.setIpAddress(request.getRemoteAddr());
        log.setMethod(request.getMethod());
        log.setUserAgent(request.getHeader("User-Agent"));
        Enumeration<String> keys = request.getParameterNames();
        StringBuilder datas = new StringBuilder();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            datas.append(key).append(" = ")
                    .append(request.getParameter(key))
                    .append("&");
        }
        log.setData(datas.toString());
        
        try{
            accessLogDAO.insert(log);
            System.out.println(log.toString());
        }catch(SQLException |ClassNotFoundException se){
            System.out.println(se.getMessage());
        }
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre
    ) {

    }

}

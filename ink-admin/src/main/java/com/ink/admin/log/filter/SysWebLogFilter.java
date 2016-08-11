package com.ink.admin.log.filter;


import com.ink.admin.common.constant.SysMapping;
import com.ink.base.log.util.LogConst;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 系统web日志过滤器
 *
 * @author aiyungui
 * @create 2016-08-02-11:37
 **/
public class SysWebLogFilter extends OncePerRequestFilter implements Filter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestUrl = request.getRequestURI();
        int sysCode = SysMapping.getSysCode(requestUrl);
        if (sysCode != -1){
            MDC.put(LogConst.REQ_SOURCE,String.valueOf(sysCode));
        }

        filterChain.doFilter(request, response);
    }
}

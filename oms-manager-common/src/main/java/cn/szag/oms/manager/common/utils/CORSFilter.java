package cn.szag.oms.manager.common.utils;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter{

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));// 允许所有域名的脚本访问该资源。
		response.setHeader("Access-Control-Allow-Methods", "POST,GET, OPTIONS, DELETE");  
		response.setHeader("Access-Control-Max-Age","3600");  
		response.setHeader("Access-Control-Allow-Headers","x-requested-with,Cache-Control,Pragma,Content-Type,Token");  
		response.setHeader("Access-Control-Allow-Credentials","true");  
        filterChain.doFilter(request, response);
    }

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}
}

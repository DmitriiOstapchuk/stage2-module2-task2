package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter ("/user/*")

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse httpResp = (HttpServletResponse) servletResponse;
            httpResp.sendRedirect("/login.jsp");
        }
    }
}
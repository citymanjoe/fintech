package com.zenpay.fintech.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getServletPath().contains("/api/v1/account/creation")) {
            chain.doFilter(request, response);
            return;
        }
        if (httpRequest.getServletPath().contains("/v3/api-docs") || httpRequest.getServletPath().contains("/swagger-ui")) {
            chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}

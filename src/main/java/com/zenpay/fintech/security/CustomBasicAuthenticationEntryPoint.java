package com.zenpay.fintech.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import java.io.IOException;

@Slf4j
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(401);
        response.sendError(401, "Permission: Unauthorized");
        log.error("Unauthorized error: {} '/' {}", authException.getMessage(), request.getServletPath());
    }

    public void afterPropertiesSet() {
        setRealmName("CORONATION.NG");
        super.afterPropertiesSet();
    }
}

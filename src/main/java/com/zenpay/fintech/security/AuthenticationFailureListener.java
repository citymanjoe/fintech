package com.zenpay.fintech.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        log.error("Login Failed: {} - {}", event.getException().getMessage(), event.getAuthentication());
        Object obj = event.getAuthentication().getPrincipal();
        String principal = Objects.toString(obj);
    }
}

package com.zenpay.fintech.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userPrincipal = (UserDetails)authentication.getPrincipal();
            System.out.println("User principal name =" + userPrincipal.getUsername());
            System.out.println("Is user enabled =" + userPrincipal.isEnabled());
        }

        Object obj = event.getAuthentication().getPrincipal();
        if (obj instanceof UserPrincipal principal) {
            log.info("Login Success :: {}", principal);
        }
    }
}

package com.deltacode.kcb.model;

import com.deltacode.kcb.entity.UserApp;
import com.deltacode.kcb.security.CustomUserDetailsService;
import org.apache.catalina.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        return Optional.ofNullable(((UserDetails) authentication.getPrincipal()).getUsername());
    }

    }



package com.skillforge.config.security.managers;

import com.skillforge.config.security.providers.ConfigAppProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConfigAppManager implements AuthenticationManager {
    private final ConfigAppProvider provider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (provider.supports(authentication.getClass())){
            return provider.authenticate(authentication);
        }
        throw new AuthenticationServiceException("Authentication not found: "+authentication.toString());
    }
}

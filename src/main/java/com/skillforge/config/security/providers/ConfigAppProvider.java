package com.skillforge.config.security.providers;

import com.skillforge.config.security.authentications.ConfigAppAuthentication;
import com.skillforge.utils.GetterValueFromApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConfigAppProvider implements AuthenticationProvider {
    ;
    private final GetterValueFromApplicationProperties getterValueFromApplicationProperties;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ConfigAppAuthentication configAppAuthentication = (ConfigAppAuthentication) authentication;

        if(getterValueFromApplicationProperties.getValue("skillForge.isReady").equals("true")){
            return new ConfigAppAuthentication(true);
        }
        throw new BadCredentialsException("App is not ready");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ConfigAppAuthentication.class.equals(authentication);
    }
}

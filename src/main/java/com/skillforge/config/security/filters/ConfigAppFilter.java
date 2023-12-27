package com.skillforge.config.security.filters;

import com.skillforge.config.security.authentications.ConfigAppAuthentication;
import com.skillforge.config.security.managers.ConfigAppManager;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
@Component
@AllArgsConstructor
public class ConfigAppFilter extends OncePerRequestFilter {
    private final ConfigAppManager configAppManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        ConfigAppAuthentication configAppAuthentication = new ConfigAppAuthentication(false);
        var a = configAppManager.authenticate(configAppAuthentication);
        if(a.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request,response);
        }else {

            throw new AuthenticationException("");
        }
    }
}

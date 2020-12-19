package com.example.bookmark.auth;

import com.example.bookmark.common.Constant;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ClientAuthInterceptor {

    @Before("@within(com.example.bookmark.auth.ClientAuthentication) "
            + "|| @annotation(com.example.bookmark.auth.ClientAuthentication)")
    public void authenticateClient(JoinPoint joinPoint) throws Exception {
        String key = getHeader("client_token");
        if(StringUtils.isBlank(key)) {
            throw new Exception("Token not present !");
        }
        else if(!key.equals(Constant.SERVICE_TOKEN)) {
            throw new Exception("Client not Authorized !");
        }
    }

    private String getHeader(String header) {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest().getHeader(header);
        } catch (Exception e) {
            return null;
        }
    }
}

package com.haha.wormholeadmin.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haha.wormholeadmin.config.MyWebDelegatingSubject;
import com.haha.wormholeadmin.enums.SystemEnum;
import com.haha.wormholeadmin.realm.LoginRealm;
import com.haha.wormholeadmin.util.JwtUtil;
import com.haha.wormholeadmin.vo.WormholeResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Slf4j
public class TokenFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest re = WebUtils.toHttp(request);
        String token = re.getHeader("Authorization");
        String username = null;
        boolean access = true;
        try {
            username = JwtUtil.parseToken(token);
            if (StringUtils.isBlank(username)) {
                access = false;
            }
        } catch (SignatureException e) {
            log.error(username + "-->错误的token", e);
            access = false;
        } catch (ExpiredJwtException e) {
            log.error(username + "-->token过期", e);
            access = false;
        } catch (Exception e) {
            log.error(username + "-->token解析失败", e);
            access = false;
        }
        if (access) {
            MyWebDelegatingSubject subject = (MyWebDelegatingSubject) SecurityUtils.getSubject();
            subject.setPrincipals(new SimplePrincipalCollection(Arrays.asList(username), LoginRealm.REALM_NAME));
            subject.setAuthenticated(true);
        }
        return access;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = WebUtils.toHttp(response);
        ObjectMapper objectMapper = new ObjectMapper();
        res.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(res.getWriter(), WormholeResponse.buildErrorByEnum(SystemEnum.LOGIN_TIMEOUT));
        return false;
    }
}

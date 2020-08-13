package com.haha.wormholeadmin.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyWebDelegatingSubject extends WebDelegatingSubject {

    public MyWebDelegatingSubject(PrincipalCollection principals, boolean authenticated,
                                String host, Session session, boolean sessionEnabled,
                                ServletRequest request, ServletResponse response,
                                SecurityManager securityManager) {
        super(principals, authenticated, host, session, sessionEnabled, request, response, securityManager);

    }

    public void setPrincipals(PrincipalCollection principals) {
        this.principals = principals;
    }
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}

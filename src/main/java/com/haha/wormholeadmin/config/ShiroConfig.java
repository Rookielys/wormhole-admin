package com.haha.wormholeadmin.config;

import com.haha.wormholeadmin.filter.CaptchaFilter;
import com.haha.wormholeadmin.filter.TokenFilter;
import com.haha.wormholeadmin.realm.LoginRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public CredentialsMatcher myCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(32);
        return hashedCredentialsMatcher;
    }

    @Bean
    public Realm loginRealm(CredentialsMatcher credentialsMatcher) {
        LoginRealm loginRealm = new LoginRealm();
        loginRealm.setCredentialsMatcher(credentialsMatcher);
        return loginRealm;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关闭session
        securityManager.setSubjectFactory(new StatelessSubjectFactory());
        DefaultWebSessionStorageEvaluator webEvalutator = (DefaultWebSessionStorageEvaluator) ((DefaultSubjectDAO) securityManager.getSubjectDAO()).getSessionStorageEvaluator();
        webEvalutator.setSessionStorageEnabled(false);
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //只有在相应的过滤器中这些url才起作用
        //factoryBean.setLoginUrl("/sys/login");
        //factoryBean.setSuccessUrl("");
        //factoryBean.setUnauthorizedUrl("/unauthorized");
        Map<String, Filter> filters = factoryBean.getFilters();
        filters.put("captcha", new CaptchaFilter());
        filters.put("token", new TokenFilter());
        LinkedHashMap<String, String> auth = new LinkedHashMap<>();
        // 注意这里的路径一定是去掉servlet context path 即应用根路径后的路径
        // 因为shiro根据url匹配过滤器链的时候获取的uri就是去掉根路径后的路径
        auth.put("/verificationCode", "anon");
        auth.put("/login", "captcha");
        auth.put("/**", "token");
        factoryBean.setFilterChainDefinitionMap(auth);
        return factoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"getLifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}

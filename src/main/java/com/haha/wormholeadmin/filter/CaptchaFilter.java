package com.haha.wormholeadmin.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haha.wormholeadmin.vo.WormholeResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CaptchaFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String key = request.getParameter("key");
        String verifyCode = request.getParameter("verifyCode");
        Cache cache = null;
        try {
            CacheManager cacheManager = WebApplicationContextUtils.getWebApplicationContext(httpRequest.getServletContext())
                    .getBean("cacheManager", CacheManager.class);
            cache = cacheManager.getCache("captcha");
            String captcha = cache.get(key, String.class);
            if (StringUtils.isBlank(key) || StringUtils.isBlank(verifyCode) || StringUtils.isBlank(captcha)) {
                return false;
            }
            return verifyCode.toLowerCase().equals(captcha);
        } catch (Exception e) {
            log.error("验证码验证失败", e);
            return false;
        } finally {
            if (cache != null && !StringUtils.isBlank(key)) {
                cache.evict(key);
            }
        }

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = WebUtils.toHttp(response);
        ObjectMapper objectMapper = new ObjectMapper();
        res.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(res.getWriter(), WormholeResponse.buildError("验证码错误"));
        return false;
    }

}

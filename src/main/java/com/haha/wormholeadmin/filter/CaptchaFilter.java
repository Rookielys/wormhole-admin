package com.haha.wormholeadmin.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haha.wormholeadmin.constant.SystemConstants;
import com.haha.wormholeadmin.vo.WormholeResponse;
import com.haha.wormholeadmin.vo.LoginUserVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaptchaFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        // json 数据需要从流中获取
        ObjectMapper objectMapper = new ObjectMapper();
        LoginUserVO loginUserVO = objectMapper.readValue(httpRequest.getInputStream(), LoginUserVO.class);
        if (loginUserVO == null) {
            return false;
        }
        String captcha = (String)SecurityUtils.getSubject().getSession().getAttribute(SystemConstants.SESSION_VERIFICATION_CODE);
        if (StringUtils.isEmpty(loginUserVO.getVerifyCode()) || StringUtils.isEmpty(captcha)) {
            return false;
        }
        return loginUserVO.getVerifyCode().equals(captcha);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //WebUtils.toHttp(request).getRequestDispatcher("/captchaError").forward(request, response);
        HttpServletResponse res = WebUtils.toHttp(response);
        ObjectMapper objectMapper = new ObjectMapper();
        res.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(res.getWriter(), WormholeResponse.buildError("验证码错误"));
        return false;
    }

}

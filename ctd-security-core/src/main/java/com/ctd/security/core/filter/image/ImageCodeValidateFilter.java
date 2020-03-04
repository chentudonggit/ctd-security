package com.ctd.security.core.filter.image;

import com.ctd.security.base.constant.ConstantCommon;
import com.ctd.security.base.exception.validate.ValidateCodeException;
import com.ctd.security.base.handler.failure.CustomAuthenticationFailureHandler;
import com.ctd.security.base.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图片参数校验
 *
 * @author chentudong
 * @date 2020/3/3 1:35 下午
 * @since 1.0
 */
@Component
public class ImageCodeValidateFilter extends OncePerRequestFilter
{
    public static final String POST = "post";
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        // 1. 如果是post方式 的登录请求，则校验输入的验证码是否正确
        if (securityProperties.getAuthentication().getLoginProcessingUrl()
                .equals(request.getRequestURI())
                && POST.equalsIgnoreCase(request.getMethod()))
        {
            try
            {
                // 校验验证码合法性
                validate(request);
            } catch (AuthenticationException e)
            {
                // 交给失败处理器进行处理异常
                customAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
                // 一定要记得结束
                return;
            }
        }
        // 放行请求
        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request)
    {
        // 先获取seesion中的验证码
        // 获取用户输入的验证码
        String sessionCode = (String) request.getSession().getAttribute(ConstantCommon.SESSION_KEY_IMAGE_CODE);
        String inputCode = request.getParameter(ConstantCommon.CODE);
        // 判断是否正确
        if (StringUtils.isBlank(inputCode))
        {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (!inputCode.equalsIgnoreCase(sessionCode))
        {
            throw new ValidateCodeException("验证码错误");
        }
    }
}
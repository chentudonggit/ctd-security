package com.ctd.security.base.handler.failure;

import com.ctd.security.base.enums.response.ResponseType;
import com.ctd.security.base.properties.SecurityProperties;
import com.ctd.security.base.vo.result.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 错误处理
 *
 * @author chentudong
 * @date 2020/3/3 6:06 下午
 * @since 1.0
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler
{
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException
    {
        ResponseType responseType = securityProperties.getAuthentication().getResponseType();
        if (ResponseType.Json.equals(responseType))
        {
            // 认证失败状态码 401
            int value = HttpStatus.UNAUTHORIZED.value();
            ResultVO result = ResultVO.build(value, exception.getMessage());
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(value);
            response.getWriter().write(result.toJsonString());
        }
        // 重写向回认证页面，注意加上 ?error
        super.setDefaultFailureUrl(securityProperties.getAuthentication().getLoginPage() + "?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}

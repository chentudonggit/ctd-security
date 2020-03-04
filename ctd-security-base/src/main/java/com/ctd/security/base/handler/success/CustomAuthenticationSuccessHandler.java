package com.ctd.security.base.handler.success;

import com.ctd.security.base.enums.response.ResponseType;
import com.ctd.security.base.properties.SecurityProperties;
import com.ctd.security.base.vo.result.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理
 *
 * @author chentudong
 * @date 2020/3/3 6:04 下午
 * @since 1.0
 */
@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 认证成功后处理逻辑
     *
     * @param authentication 封装了用户信息 UserDetails，访问IP等
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication
            authentication) throws IOException, ServletException
    {
        ResponseType responseType = securityProperties.getAuthentication().getResponseType();
        if (ResponseType.Json.equals(responseType))
        {
            // 当认证成功后，响应 JSON 数据给前端
            ResultVO result = ResultVO.ok("认证成功");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(result.toJsonString());

        } else
        {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}

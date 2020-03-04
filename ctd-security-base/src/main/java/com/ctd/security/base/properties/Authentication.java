package com.ctd.security.base.properties;

import com.ctd.security.base.enums.response.ResponseType;

/**
 * Authentication
 *
 * @author chentudong
 * @date 2020/3/3 5:57 下午
 * @since 1.0
 */
public class Authentication
{
    /**
     * 登录跳转页面
     */
    private String loginPage;

    /**
     * 登录地址
     */
    private String loginProcessingUrl;

    /**
     * 静态资源开发
     */
    private String[] staticPaths = {};

    /**
     * 开放url
     */
    private String[] antMatchers = {};

    /**
     * 返回类型
     */
    private ResponseType responseType = ResponseType.Json;

    public String getLoginPage()
    {
        return loginPage;
    }

    public void setLoginPage(String loginPage)
    {
        this.loginPage = loginPage;
    }

    public String getLoginProcessingUrl()
    {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl)
    {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    public String[] getAntMatchers()
    {
        return antMatchers;
    }

    public void setAntMatchers(String[] antMatchers)
    {
        this.antMatchers = antMatchers;
    }

    public String[] getStaticPaths()
    {
        return staticPaths;
    }

    public void setStaticPaths(String[] staticPaths)
    {
        this.staticPaths = staticPaths;
    }

    public ResponseType getResponseType()
    {
        return responseType;
    }

    public void setResponseType(ResponseType responseType)
    {
        this.responseType = responseType;
    }
}

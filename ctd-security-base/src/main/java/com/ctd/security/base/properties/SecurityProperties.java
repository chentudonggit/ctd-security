package com.ctd.security.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * SecurityProperties
 *
 * @author chentudong
 * @date 2020/3/3 5:57 下午
 * @since 1.0
 */
@Component
@ConfigurationProperties(prefix = "ctd.security")
public class SecurityProperties
{
    /**
     * 安全配置
     */
    private Authentication authentication;

    public Authentication getAuthentication()
    {
        return authentication;
    }

    public void setAuthentication(Authentication authentication)
    {
        this.authentication = authentication;
    }
}

package com.ctd.security.core.config.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * 信息配置
 *
 * @author chentudong
 * @date 2020/3/3 9:15 上午
 * @since 1.0
 */
@Configuration
public class ReloadMessageConfig
{
    @Bean
    public ReloadableResourceBundleMessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //.properties 不要加到后面, 默认加.properties，
        // 如果加了会拼接上classpath:org/springframework/security/messages_zh_CN.properties.properties
        //导致找不到文件
        messageSource.setBasename("classpath:org/springframework/security/messages_zh_CN");
        return messageSource;
    }
}

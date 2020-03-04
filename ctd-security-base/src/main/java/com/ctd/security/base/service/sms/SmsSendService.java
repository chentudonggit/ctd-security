package com.ctd.security.base.service.sms;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 短信服务
 *
 * @author chentudong
 * @date 2020/3/3 4:23 下午
 * @since 1.0
 */
@RequestMapping("smsSendService")
public interface SmsSendService
{
    /**
     * 发送短信
     *
     * @param mobile  mobile
     * @param content content
     * @return Boolean
     */
    @RequestMapping("sendSms")
    Boolean sendSms(@RequestParam("mobile") String mobile,
                    @RequestParam("content") String content);
}

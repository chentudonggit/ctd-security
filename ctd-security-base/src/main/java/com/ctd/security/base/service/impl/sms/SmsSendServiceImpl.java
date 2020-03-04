package com.ctd.security.base.service.impl.sms;

import com.ctd.security.base.service.sms.SmsSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * SmsSendServiceImpl
 *
 * @author chentudong
 * @date 2020/3/3 4:26 下午
 * @since 1.0
 */
@RestController
public class SmsSendServiceImpl implements SmsSendService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSendServiceImpl.class);
    /**
     * 发送短信
     *
     * @param mobile  mobile
     * @param content content
     * @return Boolean
     */
    @Override
    public Boolean sendSms(String mobile, String content)
    {
        String sendContent = String.format("ctd平台，温馨提示，验证码%s，请勿泄露他人。", content);
        LOGGER.info("向手机号" + mobile + "发送的短信为:" + sendContent);
        return true;
    }
}

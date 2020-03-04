package com.ctd.security.base.exception.auth;

import org.springframework.security.core.AuthenticationException;

/**
 * 参数异常
 *
 * @author chentudong
 * @date 2020/3/3 6:15 下午
 * @since 1.0
 */
public class CustomAuthenticationException extends AuthenticationException
{
    /**
     * Constructs an {@code AuthenticationException} with the specified message and root
     * cause.
     *
     * @param msg the detail message
     * @param t   the root cause
     */
    public CustomAuthenticationException(String msg, Throwable t)
    {
        super(msg, t);
    }

    /**
     * Constructs an {@code AuthenticationException} with the specified message and no
     * root cause.
     *
     * @param msg the detail message
     */
    public CustomAuthenticationException(String msg)
    {
        super(msg);
    }
}

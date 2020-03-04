package com.ctd.security.base.exception.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * ValidateCodeException
 *
 * @author chentudong
 * @date 2020/3/3 6:14 下午
 * @since 1.0
 */
public class ValidateCodeException extends AuthenticationException
{
    /**
     * Constructs an {@code AuthenticationException} with the specified message and root
     * cause.
     *
     * @param msg the detail message
     * @param t   the root cause
     */
    public ValidateCodeException(String msg, Throwable t)
    {
        super(msg, t);
    }

    /**
     * Constructs an {@code AuthenticationException} with the specified message and no
     * root cause.
     *
     * @param msg the detail message
     */
    public ValidateCodeException(String msg)
    {
        super(msg);
    }
}

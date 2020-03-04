package com.ctd.security.base.service.impl.user;

import com.ctd.security.base.exception.auth.CustomAuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * CustomUserDetailsService
 *
 * @author chentudong
 * @date 2020/3/2 5:23 下午
 * @since 1.0
 */
@Component("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService
{
    private static final String CHEN_TD = "chentd";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if(!CHEN_TD.equalsIgnoreCase(username))
        {
            throw new CustomAuthenticationException("账号或密码错误，请重试。");
        }
        return new User(username,
                "$2a$10$bA6H772LcCsgGOivKSafBunVroJa1ucYgBWqwqV2mvG9ub4zZx8Aa",
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}

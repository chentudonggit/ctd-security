package com.ctd.security.core.config;

import com.ctd.security.base.properties.Authentication;
import com.ctd.security.base.properties.SecurityProperties;
import com.ctd.security.core.filter.image.ImageCodeValidateFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全配置
 *
 * @author chentudong
 * @date 2020/3/2 11:58 上午
 * @ EnableWebSecurity 开启链路，servlet filter 实现
 * @since 1.0
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringSecurityConfig.class);
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private UserDetailsService customUserDetailsService;
    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private ImageCodeValidateFilter imageCodeValidateFilter;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * 身份认证管理器
     * 可以采取内存 / 数据库
     * //内存方式
     * //        auth.inMemoryAuthentication()
     * //                //用户名
     * //                .withUser("chentd")
     * //                //密码
     * //                .password("$2a$10$bA6H772LcCsgGOivKSafBunVroJa1ucYgBWqwqV2mvG9ub4zZx8Aa")
     * //                //权限
     * //                .authorities("admin");
     *
     * @param auth auth
     * @throws Exception Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //用户自定义方式
        auth.userDetailsService(customUserDetailsService);
    }

    /**
     * 静态资源
     *
     * @param web web
     */
    @Override
    public void configure(WebSecurity web)
    {
        web.ignoring().antMatchers(securityProperties.getAuthentication().getStaticPaths());
    }

    /**
     * 设置认证资源
     * .antMatchers( "/user/**", "/xxx/").permitAll() 放行，不校验
     *
     * @param http http
     * @throws Exception Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        Authentication authentication = securityProperties.getAuthentication();
        http.addFilterBefore(imageCodeValidateFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                //跳转的页面
                .loginPage(authentication.getLoginPage())
                //登录接口
                .loginProcessingUrl(authentication.getLoginProcessingUrl())
                //认证成功处理
                .successHandler(customAuthenticationSuccessHandler)
                //认证失败处理
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                //放行请求
                .antMatchers(authentication.getAntMatchers()).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //跨站请求伪造 关闭 CSRF 攻击
                .csrf().disable();
    }
}

package com.lsy.security.security;

import com.lsy.common.utils.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author : Lo Shu-ngan
 * @Classname DefaultPasswordEncoder
 * @Description 密码的处理方法类型
 * @Date 2020/08/17 16:29
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }

    /**
     * @param strength
     *            the log rounds to use, between 4 and 31
     */
    public DefaultPasswordEncoder(int strength) {

    }

    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.encrypt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.encrypt(rawPassword.toString()));
    }
}
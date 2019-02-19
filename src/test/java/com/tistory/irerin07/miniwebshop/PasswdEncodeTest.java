package com.tistory.irerin07.miniwebshop;

import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswdEncodeTest {
    @Test
    public void encode()throws Exception{
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = passwordEncoder.encode("359751");
        System.out.println(encode);
    }
}
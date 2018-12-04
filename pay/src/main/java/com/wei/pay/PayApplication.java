package com.wei.pay;

import com.wei.service.aop.IsLoginAop;
import com.wei.service.aop.MustLoginAop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

    @Bean
    public IsLoginAop getIsLoginAop() {
        return new IsLoginAop();
    }

    @Bean
    public MustLoginAop getMustLoginAop() {
        return new MustLoginAop();
    }
}


package com.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Spring MVC 설정을 담당
@EnableScheduling // 스케줄링 기능 활성화
public class MvcConfig implements WebMvcConfigurer {
}

package lk.edu.esoft.alsskillminercloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BasicConfig {

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public MultipartResolver multipartResolver() {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("utf-8");
//        resolver.setMaxUploadSize(10240000);
//        return resolver;
//    }

}

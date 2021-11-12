package com.epam.demo.spring.dbaccess.config;

import com.epam.demo.spring.dbaccess.dao.SessionDao;
import com.epam.demo.spring.dbaccess.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JpaConfig.class)
public class DaoConfig {

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public SessionDao sessionDao() {
        return new SessionDao();
    }


}

package com.epam.demo.spring.dbaccess;

import com.epam.demo.spring.dbaccess.config.DaoConfig;
import com.epam.demo.spring.dbaccess.dao.SessionDao;
import com.epam.demo.spring.dbaccess.dao.UserDao;
import com.epam.demo.spring.dbaccess.entities.Session;
import com.epam.demo.spring.dbaccess.entities.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class JpaDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DaoConfig.class);

        UserDao userDao = context.getBean(UserDao.class);
        SessionDao sessionDao = context.getBean(SessionDao.class);

        Arrays.stream("Jin Suga J-Hope RM Jimin V Jungkook".split(" "))
                .map(name -> new User(name, name.toLowerCase()))
                .forEach(userDao::create);


        userDao.findAll().forEach(System.out::println);

        sessionDao.create(new Session(LocalDateTime.now(), userDao.findByName("Suga")));
        sessionDao.create(new Session(LocalDateTime.now().minusDays(1), userDao.findByName("Jin")));
        sessionDao.create(new Session(LocalDateTime.now().minusHours(1), userDao.findByName("Jungkook")));

        System.out.println();

        userDao.findAll().forEach(System.out::println);

    }

}
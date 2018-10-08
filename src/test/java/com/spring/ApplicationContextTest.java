package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("src\\main\\webapp\\WEB-INF\\mvc-dispatcher-servlet.xml");
        applicationContext.getBean("alarmHistoryRepository");
    }
}

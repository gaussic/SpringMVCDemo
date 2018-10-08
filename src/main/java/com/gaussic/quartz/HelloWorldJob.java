package com.gaussic.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloWorldJob implements Job {
//    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
//        log.info("This is a first spring combine quartz !");
//        log.info("Welcome to Spring_Quartz World!" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        log.info("Let's begin ! \n \n");
        System.out.println(new Date());
    }
}
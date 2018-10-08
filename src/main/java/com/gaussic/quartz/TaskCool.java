package com.gaussic.quartz;

import com.gaussic.dataGet.PipingGetData;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
//@Service 都可以
//public class TaskCool extends QuartzJobBean{
public class TaskCool {
    /**
     * 第一个定时器测试方法
     */
    public void testJob() {
//        System.out.println("test first taskJob .... " + new Date());
        new PipingGetData().start();
    }
//
//    @Override
//    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        // 获取参数
//        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
//        String name = (String) mergedJobDataMap.get("name");
//        System.out.println(new Date().toLocaleString() + "定时任务执行中 ......" + "传递的参数为" + name);
//        System.out.println("test first taskJob .... " + new Date());
//        new PipingGetData().start();
//    }
}

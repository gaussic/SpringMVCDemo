package com.gaussic.quartz3;

import com.gaussic.dataGet.PipingGetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Transactional
//@Service 都可以
//public class TaskCool extends QuartzJobBean{
public class TaskCool {
    @Autowired
    private PipingGetData pipingGetData;
    /**
     * 第一个定时器测试方法
     */
    public void testJob() {
//        System.out.println("test first taskJob .... " + new Date());
//        long now = new Date().getTime();
        pipingGetData.getData();
//        System.out.println(new Date().getTime() - now);
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

package me.test.dist.task;


import me.test.dist.task.asynchronous.AsynchonousCallback;
import me.test.dist.task.asynchronous.AsynchronousTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class AsynchonousTaskTest {

    @Autowired
    AsynchronousTask asynchronousTask;

    @Autowired
    AsynchonousCallback asynchonousCallback;

    @Test
    public void TestAsynchonous() throws  Exception{
        asynchronousTask.doTaskOne();
        asynchronousTask.doTaskTwo();
        asynchronousTask.doTaskThree();
    }

    @Test
    public void TestAsynchonousCallBack() throws  Exception {
        long start = System.currentTimeMillis();

        Future<String> task1 = asynchonousCallback.doTaskOne();
        Future<String> task2 = asynchonousCallback.doTaskTwo();
        Future<String> task3 = asynchonousCallback.doTaskThree();

        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环等待
                break;
            }
            Thread.sleep(1000);
        }

        long end = System.currentTimeMillis();

        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

}

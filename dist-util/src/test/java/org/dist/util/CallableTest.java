package org.dist.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableTest{
 
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		//1 FutureTask既是Future、 Runnable，又是包装了Callable( 如果是Runnable最终也会被转换为Callable )， 它是这两者的合体。
		FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return new Random().nextInt(100);
			}
		});
		new Thread(futureTask).start();
		System.out.println(futureTask.get());
		System.out.println("###########################################################");
		
		//2 Executor就是Runnable和Callable的调度容器，Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果、设置结果操作
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<Integer> future = threadPool.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return new Random().nextInt(100);
			}
		});
		//get方法会阻塞，直到任务返回结果
		System.out.println(future.get());
		System.out.println("###########################################################");
		threadPool.shutdown();
		
		//3 Future是按照添加的顺序排列的
		ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
		List<Future<Integer>> list = new ArrayList<>();
		for(int i=0;i<5;i++){
			list.add(threadPool1.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return new Random().nextInt(100);
				}
			}));
		}
		for(Future<Integer> fs:list){
			System.out.println(fs.get());
		}
		System.out.println("------------------------");
		
		ArrayList<Callable<Integer>> arrayList = new ArrayList<Callable<Integer>>();
		for(int i=0;i<5;i++){
			arrayList.add(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return new Random().nextInt(100);
				}
			});
		}
		List<Future<Integer>> result = threadPool1.invokeAll(arrayList);
		for(Future<Integer> fs:result){
			System.out.println(fs.get());
		}
		System.out.println("###########################################################");
		threadPool1.shutdown();
		
		//4  提交到CompletionService中的Future是按照完成的顺序排列的
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		CompletionService<Integer> cs = new ExecutorCompletionService<>(executorService);
		for(int i=0;i<5;i++){
			final int id = i;
			cs.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return id;
				}
			});
		}
		for(int i=0;i<5;i++){
			System.out.println(cs.take().get());
		}
		System.out.println("###########################################################");
		executorService.shutdown();
	}
}

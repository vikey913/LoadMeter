package com.loadmeter.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.loadmeter.util.CmdUtil;
import com.loadmeter.vo.ThreadPoolVO;

public class WorkerPool {

    public static void main(String args[]) throws Exception{
    	ThreadPoolVO threadArgs = CmdUtil.parse(args);
    	
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(threadArgs.getCorePool(), threadArgs.getMaximumPool(), 10, 
        		TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(threadArgs.getTotalRequest()), threadFactory, rejectionHandler);

        MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
        
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        
        for(int i=0; i< threadArgs.getTotalRequest(); i++){
            executorPool.execute(new RestWorkerThread(threadArgs.getRestURI(), threadArgs.getBody()));
        }
        
        Thread.sleep(30000);
        executorPool.shutdown();
        Thread.sleep(5000);
        monitor.shutdown();
    }
}
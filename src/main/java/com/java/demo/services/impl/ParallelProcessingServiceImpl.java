package com.java.demo.services.impl;

import com.java.demo.datastore.model.UserModel;
import com.java.demo.generic.MultiThreadingImpl;
import com.java.demo.services.ParallelProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class ParallelProcessingServiceImpl implements ParallelProcessingService {

    @Override
    public void multithreading() throws InterruptedException {
        //Method 1
        MultiThreadingImpl multiThreading = new MultiThreadingImpl(1);
        MultiThreadingImpl multiThreading2 = new MultiThreadingImpl(2);
        MultiThreadingImpl multiThreading3 = new MultiThreadingImpl(3);

        /*multiThreading.start();
        multiThreading2.start();*/

        //Method 2
        //remove extends Thread and implement Runnable interface
/*        Thread thread1 = new Thread(multiThreading);
        Thread thread2 = new Thread(multiThreading2);
        Thread thread3 = new Thread(multiThreading3);
        thread1.start();
        thread2.start();
        thread3.start();*/


        //Additional Info
        //Advantages of implementing runnable over extending thread
        //.join()
        //.isAlive()

        //Method 3
        Long start = System.currentTimeMillis();
        for (int i = 0; i <= 10; i++) {

            //AtomicReference<Integer> threadSleepMs = new AtomicReference<>(5000);

            int finalI = i;
            CompletableFuture.runAsync(() -> {

                try {
                    //System.out.println(finalI +" from thread 1");
                    multiThreading.callTestApi();
                } catch (Exception e) {

                }
            });
        }
        Long end = System.currentTimeMillis();
        log.info("" + (end - start) + "ms for async execution");
    }

    @Override
    public void streams(){
        Long start = 0L;
        Long end = 0L;

        //Sequential Plain stream
        start = System.currentTimeMillis();
        IntStream.range(1,100).forEach(System.out::println);
        end = System.currentTimeMillis();

        log.info("Sequential stream took: "+(end-start)+"ms");

        //Parallel stream
        start = System.currentTimeMillis();
        IntStream.range(1,100).parallel().forEach(System.out::println);
        end = System.currentTimeMillis();

        log.info("Parallel stream took: "+(end-start)+"ms");


        //Stream API
        //Create List of Integer(threadNumber) from List of MultithreadingImpl pojo
        List<Integer> strArray = Arrays.asList(new MultiThreadingImpl(1), new MultiThreadingImpl(2))
                .stream()
                .map(MultiThreadingImpl::getThreadNumber)
                .collect(Collectors.toList());

        //Create map of object id and username from list of UserModel pojo
        Map<String, Object> mapOfIdAndUsername = Arrays.asList(
                        new UserModel(ObjectId.get(), "kmj", "pwd", "admin", true),
                        new UserModel(ObjectId.get(), "andy", "pwd", "admin,user", true))
                .stream()
                .collect(Collectors.toMap
                        (p->p.getId().toString(),UserModel::getUserName)
                );
    }
}

package com.java.demo.controller;

import com.java.demo.services.ParallelProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/parallel-processing")
public class ParallelProcessingController {

    @Autowired
    ParallelProcessingService parallelProcessingService;

    @GetMapping(value="/multi-thread")
    public void multithreading() throws InterruptedException {
        parallelProcessingService.multithreading();
    }

    @GetMapping(value="/streams")
    public void streams() throws InterruptedException {
        parallelProcessingService.streams();
    }

    @GetMapping(value = "/test")
    public String testCall() throws InterruptedException {
        Thread.sleep(3000);
        return "Hello";
    }
}

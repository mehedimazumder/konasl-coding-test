//package com.general.template.service.implementation;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.concurrent.CompletableFuture;
//
//@Service
//public class AsyncService {
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//
//    @Async("asyncExecutor")
//    public CompletableFuture<Object> getEmployeeName() throws InterruptedException
//    {
//        Object employeeNameData = restTemplate.getForObject("http://localhost:8080/name", Object.class);
//
//        Thread.sleep(1000L);  //Intentional delay
//        return CompletableFuture.completedFuture(employeeNameData);
//    }
//
//    @Async("asyncExecutor")
//    public CompletableFuture<Object> getEmployeeEmail() throws InterruptedException
//    {
//        Object employeeNameData = restTemplate.getForObject("http://localhost:8080/name", Object.class);
//
//        Thread.sleep(1000L);  //Intentional delay
//        return CompletableFuture.completedFuture(employeeNameData);
//    }
//}

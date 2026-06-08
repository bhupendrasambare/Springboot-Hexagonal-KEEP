/**
 * author @bhupendrasambare
 * Date   :03/06/26
 * Time   :11:14 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    public ExecutorService reminderExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }

    @Bean("virtualThreadExecutor")
    public Executor virtualThreadExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}

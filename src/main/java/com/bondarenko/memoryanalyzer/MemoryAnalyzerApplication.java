package com.bondarenko.memoryanalyzer;

import com.bondarenko.memoryanalyzer.service.DataService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class MemoryAnalyzerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MemoryAnalyzerApplication.class, args);

       DataService dataService = context.getBean(DataService.class);
        //dataService.generateAndSaveData();
       dataService.process();
    }

}

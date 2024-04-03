package com.example.ticketbooking;

import com.example.ticketbooking.config.ProjectConfig;
import com.example.ticketbooking.model.Theater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class TicketBookingApplication {


    public static void main(String[] args) {
        SpringApplication.run(TicketBookingApplication.class, args);
        //ProjectConfig.initialize();
//        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
//        Theater theater =context.getBean(Theater.class);
//        theater.test();
    }

}

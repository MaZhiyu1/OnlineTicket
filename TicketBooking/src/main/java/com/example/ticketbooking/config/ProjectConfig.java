package com.example.ticketbooking.config;


import com.example.ticketbooking.service.MovieService;
import com.example.ticketbooking.service.UserService;
import lombok.extern.slf4j.Slf4j;
import com.example.ticketbooking.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@Slf4j
@ComponentScan(basePackages = "com.example.ticketbooking.model")
public class ProjectConfig {

    @Autowired
    private MovieService movieService;

    @Bean
    int seatNum(){
        return 10;
    }

    @Bean
    ArrayList<Movie> movies(){
        log.info(movieService.getAll().toString());
        return (ArrayList<Movie>) movieService.getAll();
    }

}

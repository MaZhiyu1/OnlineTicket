package com.example.ticketbooking.controller;

import com.example.ticketbooking.model.User;
import com.example.ticketbooking.model.Movie;
import com.example.ticketbooking.model.Theater;
import com.example.ticketbooking.model.Ticket;
import com.example.ticketbooking.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.HashSet;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    Theater theater;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        HashSet<Integer>set = new HashSet<>();
        for(User user : userService.getAllUsers()){
            set.add(user.getTicketId());
        }
        for(Ticket ticket: theater.getTickets()){
            if(set.contains(ticket.getId())){
                ticket.setBook(true);
            }
        }
    }


    @RequestMapping(value={"", "/", "/home"})
    public String displayHomePage(Model model) {
        model.addAttribute("movies",theater.getMovies());
        model.addAttribute("pictures",theater.getFiles());
        model.addAttribute("tickets",theater.getHm());
        return "home.html";
    }


    @PostMapping("/viewDetail")
    public String displayDetail(@RequestParam("movieId") int movieId,Model model) {
        log.info("Clicked movie ID: " + movieId);
        Movie movie = theater.getMovie(movieId);
        log.info(movie.toString());
        ArrayList<Ticket> tickets = theater.getHm().get(movie);
        log.info(tickets.toString());
        model.addAttribute("movie",movie);
        model.addAttribute("tickets",tickets);
        return "viewDetail.html";
    }


    @PostMapping("/submitForm")
    public String submitForm(@RequestParam(value = "selectedOptions", required = false) Integer selectedOptions, @RequestParam("movieId") int movieId,@RequestParam("email") String email) {
        // 处理接收到的选项值
        Movie movie = theater.getMovie(movieId);
        ArrayList<Ticket> tickets = theater.getHm().get(movie);
        if (selectedOptions != null) {
                log.info("Selected option ID: " + selectedOptions);
                for(Ticket ticket : tickets){
                    if(ticket.getId()==selectedOptions){
                        if(ticket.isBook()) return "failure.html";
                    }
                }
                for(Ticket ticket : tickets){
                    if(ticket.getId()==selectedOptions){
                        ticket.setBook(true);
                        log.info(email);
                        userService.createUser(new User(selectedOptions,email));
                        log.info("Selected option ID: " + ticket.isBook());
                    }
                }

        }
        log.info(userService.getAllUsers().toString());
        return "success.html";
    }

    @RequestMapping(value={"info"})
    public String displayRegisterPage(Model model) {
        model.addAttribute("users",userService.getAllUsers());
        return "info.html";
    }

    @PostMapping("/delete")
    public String submitForm(@RequestParam(value = "delete", required = false) Long user) {
        User user1 = userService.getUserById(user);
        userService.deleteUser(user);
        for(Ticket ticket : theater.getTickets()){
            if(ticket.getId()==user1.getTicketId()){
                ticket.setBook(false);
                break;
            }
        }
        return "redirect:/info";
    }




    }

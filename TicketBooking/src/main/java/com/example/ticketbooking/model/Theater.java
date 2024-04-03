package com.example.ticketbooking.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Data
@Slf4j
@Component
//@ApplicationScope
public class Theater {
    private ArrayList<Movie> movies;
    private final ArrayList<Seat> seats;
    private ArrayList<Ticket> tickets;
    private HashMap<Movie,ArrayList<Ticket>> hm;
    private ArrayList<String> files;


    @Autowired
    public Theater(@Qualifier("movies") ArrayList<Movie> movies, @Qualifier("seatNum") int seatNum) {
        hm= new HashMap<>();
        files = new ArrayList<>();
        tickets=new ArrayList<>();
        this.movies=movies;
        seats = new ArrayList<>();
        for(int i=0;i<seatNum;i++){
            seats.add(new Seat(i));
        }
        addTicket();
        System.out.println(tickets.size());
        getFiles("C:\\Users\\15469\\Desktop\\NEU\\Coding\\Spring\\spring-3.1.2\\spring-3.1.2\\TicketBooking\\src\\main\\resources\\static\\assets\\images");
    }

    public void addTicket(){
        int count=0;
        for(Movie movie : movies){
            ArrayList<Ticket> tempTicket = new ArrayList<>();
            for(Seat seat : seats){
                tempTicket.add(new Ticket(count,seat,movie));
                count++;
            }
            hm.put(movie,tempTicket);
            tickets.addAll(tempTicket);
        }

//        for(ArrayList<Ticket> tickets1 : hm.values()){
//            System.out.println(tickets1.size());
//        }

    }


    public Movie getMovie(int movieId){
        for(Movie movie : movies){
            if(movieId==movie.getId()) return movie;
        }
        return null;
    }

    public ArrayList<Ticket> getTickets(Movie movie){
        return hm.get(movie);
    }


    public ArrayList<String> getFiles(String path) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
            }
        }
        return files;
    }









    public void test(){
        //tes Movie collection
        System.out.println("Seat Info: ");
        for(Seat seat : seats){
            log.info(seat.toString());
        }

        System.out.println("Movie Info: ");
        for(Movie movie : movies){
            log.info(movie.toString());
        }

        System.out.println("Image Info: ");

    }

}

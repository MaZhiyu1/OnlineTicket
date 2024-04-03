package com.example.ticketbooking.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Ticket {
    private int id;
    private Seat seat;
    private Movie movie;
    private boolean isBook=false;
    public Ticket() {

    }

    public Ticket(int id, Seat seat, Movie movie) {
        this.id=id;
        this.seat = seat;
        this.movie = movie;
    }

    public boolean Order(){
        if(isBook) return false;
        isBook=true;
        return true;
    }



}

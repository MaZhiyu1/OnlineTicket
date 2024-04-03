package com.example.ticketbooking.model;

import lombok.Data;

@Data
public class Seat {
    private int seatId;

    public Seat(int seatId) {
        this.seatId = seatId;
    }
}

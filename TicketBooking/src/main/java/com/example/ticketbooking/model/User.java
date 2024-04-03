package com.example.ticketbooking.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticketId")
    private int ticketId;

    @Column(name = "email")
    private String email;


    public User(int ticketId, String email) {
        this.ticketId = ticketId;
        this.email = email;
    }

    public User() {

    }
}

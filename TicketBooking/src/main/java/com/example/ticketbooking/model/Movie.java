package com.example.ticketbooking.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

@Entity
@Table(name = "movie123")
@Data
public class Movie {

    @Column(name = "name")
    private String name;

    @Column(name = "about")
    private String about;

    @Column(name = "myPicture")
    private int myPicture;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    public Movie(int id,String name, String about, int myPicture) {
        this.id=id;
        this.name = name;
        this.about = about;
        this.myPicture = myPicture;
    }

    public Movie() {

    }
}

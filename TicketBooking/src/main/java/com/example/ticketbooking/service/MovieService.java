package com.example.ticketbooking.service;

import com.example.ticketbooking.Repository.MovieRepository;
import com.example.ticketbooking.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository ;

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie getUserById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie createUser(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteUser(Long id) {
        movieRepository.deleteById(id);
    }



}

package com.lens.coursetracker;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManagerFactory;


@SpringBootApplication
public class CoursetrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursetrackerApplication.class, args);
    }
}

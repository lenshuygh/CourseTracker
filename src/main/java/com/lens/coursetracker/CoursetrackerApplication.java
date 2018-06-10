package com.lens.coursetracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CoursetrackerApplication {

    private static Logger logger = LogManager.getLogger(CoursetrackerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CoursetrackerApplication.class, args);

        System.out.println(logger.getLevel());
    }
}

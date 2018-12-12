package com.lamb.packt.spring.book.cardatabase;

import com.lamb.packt.spring.book.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/*
 * This application was made from following the book
 * "Hands-On Full Stack Development with Spring Boot 2.0 and React:
 *  Build modern and scalable full stack applications using the JAva-based Spring framework
 *  5.0 and React" by Juna Hinkula
 */
@SpringBootApplication
public class CardatabaseApplication {
    private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

    @Autowired
    private CarRepository carRepo;

    @Autowired
    private OwnerRepository ownerRepo;

    @Autowired
    private UserRepository userRepo;

    public static void main(String[] args) {
        SpringApplication.run(CardatabaseApplication.class, args);
        logger.info("Spring Boot has started, say Hello!");
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {

            Owner owner1 = new Owner("John", "Johnson");
            Owner owner2 = new Owner("Mary", "Robinson");

            ownerRepo.save(owner1);
            ownerRepo.save(owner2);

            carRepo.save( new Car("Ford", "Mustang", "Red", "ADF-1121", 2017, 59000, owner1));
            carRepo.save( new Car("Nissan", "Leaf", "White", "SSJ-3002", 2014, 29000, owner2));
            carRepo.save( new Car("Toyota", "Prius", "Blue", "KKO-2212", 2019, 60000, owner1));


            //Example users

            User user = new User("user", "$2a$10$wy/34xXxhcmM7WShwa/GOeaT/QKQ.kUeuNG6TSE9eAOWSOirdEO2a", "USER");
            User admin = new User("admin", "$2a$10$DGCyIs9c8HMHpBYlIr22wu7BCVAR7PiYZxAbDJKM7SHLciZ09V1r.", "ADMIN");

            userRepo.save(user);
            userRepo.save(admin);

        };
    }

}

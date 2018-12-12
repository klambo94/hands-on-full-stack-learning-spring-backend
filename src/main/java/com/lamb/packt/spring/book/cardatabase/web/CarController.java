package com.lamb.packt.spring.book.cardatabase.web;

import com.lamb.packt.spring.book.cardatabase.domain.Car;
import com.lamb.packt.spring.book.cardatabase.domain.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired
    private CarRepository carRepo;

    @RequestMapping(method = RequestMethod.GET, value="/cars")
    public Iterable<Car> getCars() {
        return carRepo.findAll();
    }
}

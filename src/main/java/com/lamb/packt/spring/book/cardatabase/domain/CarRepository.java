package com.lamb.packt.spring.book.cardatabase.domain;

import org.springframework.data.repository.CrudRepository;


public interface CarRepository extends CrudRepository <Car, Long> {
}

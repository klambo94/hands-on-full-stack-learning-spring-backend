package com.lamb.packt.spring.book.cardatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ownerId;

    private String firstName, lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonIgnore
    private List<Car> cars;

    public Owner() {
    }

    public Owner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public Owner setOwnerId(long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Owner setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Owner setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<Car> getCars() {
        return cars;
    }

    public Owner setCars(List<Car> cars) {
        this.cars = cars;
        return this;
    }
}

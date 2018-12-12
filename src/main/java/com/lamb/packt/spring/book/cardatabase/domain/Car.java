package com.lamb.packt.spring.book.cardatabase.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Proxy(lazy = false)
public class Car {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private String brand, model, color, registerNumber;
    private int year, price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    public Car() {
    }

    public Car(String brand, String model, String color, String registerNumber, int year, int price, Owner owner) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
        this.owner = owner;
    }

    public Car(String brand, String model, String color, String registerNumber, int year, int price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public Car setId(long id) {
        this.id = id;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Car setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Car setModel(String model) {
        this.model = model;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Car setColor(String color) {
        this.color = color;
        return this;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public Car setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Car setYear(int year) {
        this.year = year;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Car setPrice(int price) {
        this.price = price;
        return this;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}

package com.lamb.packt.spring.book.cardatabase;

import com.lamb.packt.spring.book.cardatabase.domain.Car;
import com.lamb.packt.spring.book.cardatabase.domain.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepo;

    @Test
    public void saveCar() {
        Car car = new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000);

        entityManager.persistAndFlush(car);

        assertThat(car.getId()).isNotNull();
    }

    @Test
    public void deleteCar() {
        Car car1 = new Car("Tesla", "Model X", "White", "ABC-1234", 2017, 86000);
        Car car2 = new Car("Mini", "Cooper", "Red", "DEF-5678", 2019, 80000);

        entityManager.persistAndFlush(car1);
        entityManager.persistAndFlush(car2);

        carRepo.deleteAll();
        assertThat(carRepo.findAll()).isEmpty();
    }



}

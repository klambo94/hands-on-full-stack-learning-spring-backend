package com.lamb.packt.spring.book.cardatabase;

import com.lamb.packt.spring.book.cardatabase.web.CarController;
import org.assertj.core.api.Java6Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardatabaseApplicationTests {

    @Autowired
    private CarController carController;

    @Test
    public void contextLoads() {
        Java6Assertions.assertThat(carController).isNotNull();
    }

}

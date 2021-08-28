package couponsys.pro2.couponsystem2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CouponsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponsysApplication.class, args);
    }

}

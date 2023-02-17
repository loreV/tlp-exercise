package org.tlp;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication backend = new SpringApplication(Main.class);
        backend.setBannerMode(Banner.Mode.OFF);
        backend.run(args);
    }
}
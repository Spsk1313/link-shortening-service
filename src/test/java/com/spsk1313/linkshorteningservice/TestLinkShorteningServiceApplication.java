package com.spsk1313.linkshorteningservice;

import org.springframework.boot.SpringApplication;

public class TestLinkShorteningServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(LinkShorteningServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

package com.spsk1313.linkshorteningservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class LinkShorteningServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}

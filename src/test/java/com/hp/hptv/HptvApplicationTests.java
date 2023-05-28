package com.hp.hptv;

import com.hp.utils.WebSocketConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {WebSocketConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HptvApplicationTests {

    @Test
    public void contextLoads() {

        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }

}

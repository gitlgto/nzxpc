package com.nzxpc.mem;

import com.nzxpc.mem.core.infrastructure.Cache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Cache.deleteRoleButtonMap();
        Cache.init();
    }
}

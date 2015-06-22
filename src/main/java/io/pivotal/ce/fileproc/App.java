package io.pivotal.ce.fileproc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by cq on 21/6/15.
 */
@SpringBootApplication
public class App implements CommandLineRunner{


    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}

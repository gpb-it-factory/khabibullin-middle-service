package gpb.dus.middle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "gpb")
public class KhabibullinMiddleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KhabibullinMiddleServiceApplication.class, args);
    }

}

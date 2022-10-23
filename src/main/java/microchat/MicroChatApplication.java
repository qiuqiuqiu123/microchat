package microchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MicroChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroChatApplication.class, args);
    }

}

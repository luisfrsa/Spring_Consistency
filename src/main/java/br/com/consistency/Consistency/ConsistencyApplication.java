package br.com.consistency.Consistency;

import br.com.consistency.Consistency.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsistencyApplication {

    public static void main(String[] args) {

        Config.id = Integer.parseInt(args[0]);
        Config.port = Integer.parseInt(args[1]);
        Config.numServer = Integer.parseInt(args[2]) + 1;
        Config.primary = Config.id == 0;
        SpringApplication.run(ConsistencyApplication.class, args);
    }
}

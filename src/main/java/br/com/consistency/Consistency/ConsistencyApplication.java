package br.com.consistency.Consistency;

import br.com.consistency.Consistency.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class ConsistencyApplication {

    public static void main(String[] args) {

        Config.id = Integer.parseInt(args[0]);
        Config.port = Integer.parseInt(args[1]);
        Config.numServer = Integer.parseInt(args[2]) + 1;
        Config.primary = Config.id == 0;
        Config.enabled = true;
        Config.servers = IntStream.range(0, Config.numServer).boxed().collect(Collectors.toList());

        SpringApplication.run(ConsistencyApplication.class, args);
    }
}

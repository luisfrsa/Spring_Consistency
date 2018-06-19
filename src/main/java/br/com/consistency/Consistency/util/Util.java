package br.com.consistency.Consistency.util;


import br.com.consistency.Consistency.config.Config;

import java.util.concurrent.ThreadLocalRandom;

public class Util {

    public static String getLogMessage() {
        return String.format("Request for request from me -> {id: %s, port: %s, isPrimary: %s} ", Config.id, Config.port, Config.primary);

    }

    public static void delay(){
        try {
            Thread.sleep(0 + 500 * ThreadLocalRandom.current().nextInt(2, 10 + 1));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

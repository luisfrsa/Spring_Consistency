package br.com.consistency.Consistency.util;


import br.com.consistency.Consistency.config.Config;

public class Util {

    public static String getLogMessage() {
        return String.format("Request for request from me -> {id: %s, port: %s, isPrimary: %s} ", Config.id, Config.port, Config.primary);

    }
}

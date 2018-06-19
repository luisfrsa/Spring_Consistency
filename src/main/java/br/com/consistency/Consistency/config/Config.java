package br.com.consistency.Consistency.config;

import java.util.List;

public class Config {

    public static Integer id;
    public static Integer port;
    public static Integer numServer;
    public static Integer primary;
    public static boolean enabled;
    public static List<Integer> servers;


    public static String str() {
        return "Config{" +
                "id=" + Config.id +
                ", port=" + Config.port +
                ", numServer=" + Config.numServer +
                ", primary=" + Config.primary +
                ", enabled=" + Config.enabled +
                ", servers=" + Config.servers.toString() +
                '}';
    }

}

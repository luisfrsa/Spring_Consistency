package br.com.consistency.Consistency.service;

import br.com.consistency.Consistency.config.Config;
import br.com.consistency.Consistency.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.consistency.Consistency.util.Util.*;
import static java.lang.String.format;

@Service
public class RequestService {

    private static final Logger log = LoggerFactory.getLogger(RequestService.class);


    public static void newPrimary(Integer id, Integer from) {
        Config.primary = 8080 + id;
//        Config.servers = Config.servers.stream().filter(s -> !s.equals(from)).collect(Collectors.toList());
    }

    public static void updatePrimary() {
        Optional<Integer> primary = Config.servers.stream()
                .filter(s -> !s.equals(Config.id))
                .findAny();
        if (primary.isPresent()) {
            broadCastPrimary(primary.get());
        }
        Config.primary = 8080 + primary.get();
//        Config.servers = Config.servers.stream().filter(s -> !s.equals(Config.id)).collect(Collectors.toList());
    }


    public static String request(String serverPort, String name, String getParam) {
        log.info(Config.str());
        log.info(format(getLogMessage() + " -- To:%s, Request name: %s, URL: %s", serverPort, name, getParam));
        try {
            Util.delay();
            URL url = new URL(format("http://localhost:%s/api/%s/%s", serverPort, getParam, Config.id));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void broadCastPrimary(Integer primary) {
        broadcast("Broadcast new Primary to 808", "update-primary/" + primary.toString());
    }

    private static void broadcast(String name, String param) {
        Config.servers.stream()
                .filter(s -> !s.equals(Config.id))
                .forEach(s -> request("808" + s, name + s, param));
    }

    public static void broardCastNewUser(String nome) {
        broadcast("Broadcast new User to 808", "user/addFromPrimary/" + nome);
//        Config.servers.stream()
//                .filter(s -> !Config.id.equals(s))
//                .collect(Collectors.toList())
//                .forEach(s -> request("808" + s, "Broadcast new User to 808" + s, "user/addFromPrimary/" + nome));
    }

    public static void broardCastRemoveUser(Long id) {
        broadcast("Broadcast new User to 808", "user/removeFromPrimary/" + id);
//        Config.servers.stream()
//                .filter(s -> !Config.id.equals(s))
//                .collect(Collectors.toList())
//                .forEach(s -> request("808" + s, "Broadcast new User to 808" + s, "user/removeFromPrimary/" + id));
    }


}

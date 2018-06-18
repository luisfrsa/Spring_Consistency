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
        Optional<Integer> primary = Config.servers.stream()
                .filter(s -> s != Config.id)
                .findAny();
        if (primary.isPresent()) {
            broadCastPrimary(primary.get());
        }
        if (Config.id == id) {
            Config.primary = true;
        } else {
            Config.primary = false;
        }
        Config.servers = Config.servers.stream().filter(s -> s != from).collect(Collectors.toList());
        log.error("No server found");
    }

    public static void updatePrimary() {
        Optional<Integer> primary = Config.servers.stream()
                .filter(s -> s != Config.id)
                .findAny();
        if (primary.isPresent()) {
            broadCastPrimary(primary.get());
        }
        Config.primary = false;
        Config.servers = Config.servers.stream().filter(s -> s != Config.id).collect(Collectors.toList());
        log.error("No server found");
    }

    public static void broadCastPrimary(Integer primary) {
        Config.servers.stream()
                .filter(s -> s != Config.id)
                .forEach(s -> request("808" + s, "Broadcast new Primary", "update-primary/" + primary.toString()));
    }

    public static void broardCastNewUser(String nome) {
        Config.servers.stream()
                .filter(s -> s != Config.id)
                .forEach(s -> request("808" + s, "Broadcast new User", "user/addFromPrimary/" + nome));
    }

    public static String request(String serverPort, String name, String getParam) {
        log.info(format(getLogMessage() + " -- To:%s, Request name: %s, URL: %s", serverPort, name, getParam));
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
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


}

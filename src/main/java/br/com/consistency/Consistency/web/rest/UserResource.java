package br.com.consistency.Consistency.web.rest;


import br.com.consistency.Consistency.config.Config;
import br.com.consistency.Consistency.model.User;
import br.com.consistency.Consistency.service.RequestService;
import br.com.consistency.Consistency.service.UserService;
import br.com.consistency.Consistency.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private static final String FIND_ALL = "Find all";
    private static final String ENTITY_NAME = "user";
    private static final String REST_REQUEST_FOR_SAVE = "REST request for save " + ENTITY_NAME + " %s";
    private static final String REST_REQUEST_FOR_DELETE = "REST request for delete " + ENTITY_NAME + " %s";
    private static final String CANNOT_CREATE_WITH_ID = "A new " + ENTITY_NAME + " cannot already have an ID %d";


    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/user/get/{id}/{from}")
    public ResponseEntity<User> findById(@PathVariable Long id, @PathVariable Integer from) {
        User user = userService.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/user/{from}")
    public ResponseEntity<List<User>> findAll(@PathVariable Integer from) {
//        System.out.println(new RequestService().request("8080", "users", "users"));
        List<User> users = userService.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/user/add/{nome}/{from}")
    public ResponseEntity<User> createUser(@PathVariable String nome, @PathVariable Integer from) {
        String strLog = format(REST_REQUEST_FOR_SAVE, nome);
        log.debug(strLog);
        User result = userService.save(nome);
        return new ResponseEntity<User>(result, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/user/remove/{id}/{from}")
    public ResponseEntity<Void> removeUser(@PathVariable Long id, @PathVariable Integer from) {
        String strLog = format(REST_REQUEST_FOR_DELETE, id);
        log.debug(strLog);
        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}

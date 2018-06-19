package br.com.consistency.Consistency.web.rest;

import br.com.consistency.Consistency.config.Config;
import br.com.consistency.Consistency.model.User;
import br.com.consistency.Consistency.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ServerResource {


    private final Logger log = LoggerFactory.getLogger(ServerResource.class);

    @CrossOrigin
    @GetMapping("/update-primary/{id}/{from}")
    public ResponseEntity<Void> findById(@PathVariable Integer id, @PathVariable Integer from) {
        RequestService.newPrimary(id, from);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }


    @CrossOrigin
    @GetMapping("/disable/{from}")
    public ResponseEntity<Void> disable(@PathVariable Integer from) {
        Config.enabled = false;
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/enable/{from}")
    public ResponseEntity<Void> enable(@PathVariable Integer from) {
        Config.enabled = true;
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}

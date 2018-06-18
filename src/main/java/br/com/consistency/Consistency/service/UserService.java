package br.com.consistency.Consistency.service;

import br.com.consistency.Consistency.model.User;
import br.com.consistency.Consistency.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Service
@Transactional
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private static final String REQUEST_SAVE = "Request for save User with id";
    private static final String REQUEST_UPDATE = "Request for update User with id %s";
    private static final String PERSIST_REQUESTED_WITH_NULL_OBJECT = "Persist requested with null object";

    @Autowired
    private UserRepository userRepository;

    public User persist(User user) {
        if (Objects.isNull(user)) {
            log.error(PERSIST_REQUESTED_WITH_NULL_OBJECT);
            return null;
        }
        if (user.getId() > 0) {
            return save(user);
        }
        return update(user);
    }

    public User save(String name) {
        return save(new User(name));
    }

    public User save(User user) {
        String stringDebug = format(REQUEST_SAVE);
        log.debug(stringDebug);
        return userRepository.save(user);
    }


    public User update(User user) {
        String stringDebug = format(REQUEST_UPDATE, user.getId());
        log.debug(stringDebug);
        return userRepository.save(user);
    }

    public void delete(Long id) {
        User user = findById(id);
        if (Objects.nonNull(user)) {
            userRepository.delete(user);
        }
    }

    public User findById(Long id) {
        return userRepository.findFirstById(id);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }
}

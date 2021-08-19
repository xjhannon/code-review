package com.carsaver.codereview.service;

import com.carsaver.codereview.model.User;
import com.carsaver.codereview.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toMap;

// todo: have service calls spread between here and the controller
@Service
public class UserService {
    private final UserRepository repository;

    private final EmailService emailService;

    public UserService(UserRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public List<User> findAllByOrderByIdAsc() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public User updateUserLocation(Long id) {return null;};

    public User save(User user) {return repository.save(user);};

    public void deleteById(Long userId) {};

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public User updateEmail(User user, String email) {
        try {
            // todo: can add all of these checks into one inline statement
            if (user != null) {
                if (user.getId() != null)
                    if (email != null) {
                        user.setEmail(email);
                        repository.save(user);
                        emailService.sendConfirmation(email);
                    }
                else // todo: why not have braces around the next line to be consistent?
                    return user;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        // todo: if problem with inputs above, this returns un-modified user
        return user;
    }

    public Map<Long, String> getNames() {
        // todo: stream- expect trickery here...
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(toMap(User::getId, user -> {
                    return user.getFirstName() + ", " + user.getFirstName();
                }));

    }

}

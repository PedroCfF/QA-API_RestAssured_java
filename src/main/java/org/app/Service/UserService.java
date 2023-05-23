package org.app.Service;


import org.app.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong(); // For generating unique ids

    public UserService() {
        createUser(new User(null, "John Doe", "john@example.com"));
        createUser(new User(null, "Jane Smith", "jane@example.com"));
    }

    public List<User> getUsers() {
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return users;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(()->new NoSuchElementException("No user found with id " + id));
    }

    public User createUser(User newUser) {
        Long id = nextId.incrementAndGet();
        if (newUser.getName().equals(null))
        {
            throw new RuntimeException("The user should have a name");
        }
        else if (newUser.getEmail().equals(null))
        {
            throw new RuntimeException("The user should have an email");
        }

        User user = new User(id, newUser.getName(), newUser.getEmail());
        users.add(user);
        return user;
    }

    public User updateUser(Long id, User newUser) {
        if (newUser.getName().equals(null))
        {
            throw new RuntimeException("The user should have a name");
        }
        else if (newUser.getEmail().equals(null))
        {
            throw new RuntimeException("The user should have an email");
        }

        User userToUpdate = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No user found with id " + id));

        userToUpdate.setName(newUser.getName());
        userToUpdate.setEmail(newUser.getEmail());

        return userToUpdate;
    }

    public void deleteUser(Long id) {
        if (users.stream().noneMatch(user -> user.getId().equals(id))) {
            throw new NoSuchElementException("No user found with id " + id);
        }

        users.removeIf(user -> user.getId().equals(id));
    }
}

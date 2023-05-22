package org.app.Service;


import org.app.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        return users;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User createUser(User newUser) {
        Long id = nextId.incrementAndGet(); // Generate next id
        User user = new User(id, newUser.getName(), newUser.getEmail());
        users.add(user);
        return user;
    }

    public User updateUser(Long id, User newUser) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setName(newUser.getName());
                user.setEmail(newUser.getEmail());
                return user;
            }
        }
        return null;
    }

    public void deleteUser(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}

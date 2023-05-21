package org.app.Service;


import org.app.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> users = new ArrayList<>();

    // Constructor to populate with some initial data
    public UserService() {
        users.add(new User(1L, "John Doe", "john@example.com"));
        users.add(new User(2L, "Jane Smith", "jane@example.com"));
        // Add more users if needed
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();

        return optionalUser.orElse(null);
    }

    public User createUser(User user) {
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

package org.test.Domain.Service;


import org.test.Domain.Model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}

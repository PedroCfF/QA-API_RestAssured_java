package org.app.Service;

import org.app.Model.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}

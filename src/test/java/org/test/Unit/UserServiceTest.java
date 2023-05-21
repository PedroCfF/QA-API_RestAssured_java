package org.test.Unit;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.test.Domain.Model.User;
import org.test.Domain.Service.UserService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {
    public UserService userService;

    @BeforeEach
    void setUp() {
        userService = Mockito.mock(UserService.class);
    }

    @Test
    void testGetUsers() {
        User user1 = new User(1L, "John Doe", "john@example.com");
        User user2 = new User(2L, "Jane Smith", "jane@example.com");

        Mockito.when(userService.getUsers()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getUsers();
        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("Jane Smith", users.get(1).getName());
    }

    @Test
    void testGetUserById() {
        User user1 = new User(1L, "John Doe", "john@example.com");
        User user2 = new User(2L, "Jane Smith", "jane@example.com");

        Mockito.when(userService.getUserById(1L)).thenReturn(user1);

        User user = userService.getUserById(1L);
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void testCreateUser() {
        User newUser = new User(1L, "New User", "newuser@example.com");

        Mockito.when(userService.createUser(newUser)).thenReturn(newUser);

        User user = userService.createUser(newUser);
        assertEquals(1L, user.getId());
        assertEquals("New User", user.getName());
        assertEquals("newuser@example.com", user.getEmail());
    }

    @Test
    void testUpdateUser() {
        User user1 = new User(1L, "John Doe", "john@example.com");
        User user2 = new User(2L, "Jane Smith", "jane@example.com");
        User updatedUser = new User(1L, "Updated User", "updateduser@example.com");

        Mockito.when(userService.updateUser(1L,updatedUser)).thenReturn(updatedUser);

        User user = userService.updateUser(1L,updatedUser);
        assertEquals(1L, user.getId());
        assertEquals("Updated User", user.getName());
        assertEquals("updateduser@example.com", user.getEmail());
    }

    @Test
    void testDeleteUser() {
        User user = new User(1L, "John Doe", "john@example.com");

        userService.createUser(user);

        userService.deleteUser(1L);

        assertEquals(0, userService.getUsers().size());
    }
}

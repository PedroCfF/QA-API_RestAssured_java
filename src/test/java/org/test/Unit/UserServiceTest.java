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
    void getUsers_existingUsers_passing() {
        User user1 = new User(1L, "John Doe", "john@example.com");
        User user2 = new User(2L, "Jane Smith", "jane@example.com");

        Mockito.when(userService.getUsers()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getUsers();
        assertEquals(2, users.size());
        assertEquals("John Doe", users.get(0).getName());
        assertEquals("Jane Smith", users.get(1).getName());
    }

    @Test
    void getUsers_noUsers_failing() {

    }

    @Test
    void getUserById_existingId_passing() {
        User user1 = new User(1L, "John Doe", "john@example.com");
        User user2 = new User(2L, "Jane Smith", "jane@example.com");

        Mockito.when(userService.getUserById(1L)).thenReturn(user1);

        User user = userService.getUserById(1L);
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void getUserById_unExistingId_failing() {

    }

    @Test
    void createUser_validUser_passing() {
        User newUser = new User(1L, "New User", "newuser@example.com");

        Mockito.when(userService.createUser(newUser)).thenReturn(newUser);

        User user = userService.createUser(newUser);
        assertEquals(1L, user.getId());
        assertEquals("New User", user.getName());
        assertEquals("newuser@example.com", user.getEmail());
    }

    @Test
    void createUser_invalidUser_failing() {

    }

    @Test
    void updateUser_existingIdAndValidUser_passing() {
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
    void updateUser_unExistingIdAndValidUser_failing() {

    }

    @Test
    void updateUser_ExistingIdAndInvalidUser_failing() {

    }

    @Test
    void updateUser_unExistingIdAndInvalidUser_failing() {

    }

    @Test
    void testDeleteUser_existingId_passing() {
        User user = new User(1L, "John Doe", "john@example.com");

        userService.createUser(user);

        userService.deleteUser(1L);

        assertEquals(0, userService.getUsers().size());
    }

    @Test
    void testDeleteUser_unExistingId_failing() {

    }
}

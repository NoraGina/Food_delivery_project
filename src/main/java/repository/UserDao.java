package repository;

import model.User;

import java.util.Set;

public interface UserDao {
    void createUser(User user);
    Set<User> readAllUsers();
    User findUserById(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
    User findUserByFirstNameAndLastName(String firstName, String lastName);
}

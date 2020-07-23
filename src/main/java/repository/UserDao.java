package repository;

import model.Department;
import model.User;

import java.util.Set;

public interface UserDao {
    void createUser(User user);
    Set<User> readAllUsers();
    User findUserById(Long id);
    void deleteUser(Long id);
    void updateUser(User user);
    User findUserByFirstNameAndLastName(String firstName, String lastName);
    Long isUserExist(String userName, String password);
    Long isAdministratorExist(String userName, String password, Department department);
    Long isUserNameExist(String userName);
    User findByUserNameAndPassword(String userName, String password);
    User findUserByFirstAndLastName(String firstName, String lastName);
}

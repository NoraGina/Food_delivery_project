package model;

import java.util.Objects;

public class User extends Person{
    private String userPassword;
    private Department department;

    public User() {
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return userPassword.equals(user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "userPassword='" + userPassword + '\'' +
                ", department=" + department +
                "} " + super.toString();
    }
}

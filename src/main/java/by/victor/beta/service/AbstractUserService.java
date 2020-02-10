package by.victor.beta.service;

import by.victor.beta.entity.Role;
import by.victor.beta.entity.User;
import by.victor.beta.entity.UserStatus;

import java.io.File;
import java.util.List;

public interface AbstractUserService {
    void addUser(User user) throws ServiceException;

    List<User> findUserByLogin(String login);

    List<User> findUserByRole(Role role);

    List<User> findUserByStatus(UserStatus status);

    List<User> findUserByUsername(String username);

    boolean creditUser(User user, int sum) throws ServiceException;

    boolean debitUser(User user, int sum) throws ServiceException;

    void setUserPhoto(File file, String username) throws ServiceException;

     void setUserStatus(UserStatus status, String username) throws ServiceException;
}

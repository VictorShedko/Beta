package by.victor.beta.service.userservice;

import by.victor.beta.command.PagePathProvider;
import by.victor.beta.entity.Role;
import by.victor.beta.entity.User;
import by.victor.beta.entity.UserStatus;
import by.victor.beta.repository.impl.UserRepository;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.impl.userspecification.*;
import by.victor.beta.service.FileManager;
import by.victor.beta.service.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private static final String AVATAR_FILE_NAME = "avt.jpg";


    public void addUser(User user) {
        AddUserSpecification specification = new AddUserSpecification(user);
        try {
            UserRepository.getInstance().createQuery(specification);
        } catch (RepositoryException e) {
            e.printStackTrace();//todo
        }
    }

    public List<User> findUserByLogin(String login) {
        FindUserByLoginSpecification specification = new FindUserByLoginSpecification(login);
        try {
            return UserRepository.getInstance().findQuery(specification);
        } catch (RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }

    }

    public List<User> findUserByRole(Role role) {

        FindUserByRoleSpecification specification = new FindUserByRoleSpecification(role);
        try {
            List<User> users = UserRepository.getInstance().findQuery(specification);
            return users;
        } catch (RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }

    }

    public List<User> findUserByStatus(UserStatus status) {

        FindUserByStatusSpecification specification = new FindUserByStatusSpecification(status);
        try {
            List<User> users = UserRepository.getInstance().findQuery(specification);
            return users;
        } catch (RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }

    }

    public List<User> findUserByUsername(String username) {
        FindUserByLoginSpecification specification = new FindUserByLoginSpecification(username);
        try {
            List<User> users = UserRepository.getInstance().findQuery(specification);
            return users;
        } catch (RepositoryException e) {
            e.printStackTrace();
            return List.of();
        }

    }


    public boolean creditUser(User user, int sum) throws ServiceException {
        long startSum = user.getBalance();
        ChangeBalanceSpecification modifySpecification = new ChangeBalanceSpecification(startSum + sum, user.getUsername());
        try {
            return UserRepository.getInstance().updateQuery(modifySpecification) > 0;
        } catch (RepositoryException e) {
            throw new ServiceException();
        }
    }

    public boolean debitUser(User user, int sum) throws ServiceException {
        long startSum = user.getBalance();
        if (user.getBalance() > sum) {
            ChangeBalanceSpecification modifySpecification =
                    new ChangeBalanceSpecification(startSum - sum, user.getUsername());
            try {
                return UserRepository.getInstance().updateQuery(modifySpecification) > 0;
            } catch (RepositoryException e) {
                throw new ServiceException();
            }
        } else {
            return false;
        }
    }

    public void setUserPhoto(File file, String username) throws ServiceException {
        User user = findUserByUsername(username).get(0);
        FileManager fileManager=new FileManager();
        File dest=fileManager.moveFileToUserDir(file,username,AVATAR_FILE_NAME);
        user.setPhotoPath(PagePathProvider.USER_FILES+PagePathProvider.SEPARATOR+
                user.getUsername()+PagePathProvider.SEPARATOR+dest.getName());
        ChangeUserSpecification specification = new ChangeUserSpecification(user);
        try {
            UserRepository.getInstance().updateQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException();
        }
    }

    public void setUserStatus(UserStatus status, String username) throws ServiceException {
        User user = findUserByUsername(username).get(0);
        user.setStatus(status);
        ChangeUserSpecification specification = new ChangeUserSpecification(user);
        try {
            UserRepository.getInstance().updateQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException();
        }


    }

}

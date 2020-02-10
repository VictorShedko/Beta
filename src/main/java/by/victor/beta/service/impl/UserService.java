package by.victor.beta.service.impl;

import by.victor.beta.command.PagePathProvider;
import by.victor.beta.entity.Role;
import by.victor.beta.entity.SupportedImagesFormat;
import by.victor.beta.entity.User;
import by.victor.beta.entity.UserStatus;
import by.victor.beta.repository.impl.UserRepository;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.impl.userspecification.*;
import by.victor.beta.service.AbstractUserService;
import by.victor.beta.service.FileManager;
import by.victor.beta.service.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

public class UserService implements AbstractUserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private static final String AVATAR_FILE_NAME = "avt";

    @Override
    public void addUser(User user) throws ServiceException {
        AddUserSpecification specification = new AddUserSpecification(user);
        try {
            UserRepository.getInstance().createQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUserByLogin(String login) {
        FindUserByLoginSpecification specification = new FindUserByLoginSpecification(login);
        try {
            return UserRepository.getInstance().findQuery(specification);
        } catch (RepositoryException e) {
            logger.log(Level.INFO,"usser "+login+" not exist",e);
            return List.of();
        }

    }

    @Override
    public List<User> findUserByRole(Role role) {

        FindUserByRoleSpecification specification = new FindUserByRoleSpecification(role);
        try {
            List<User> users = UserRepository.getInstance().findQuery(specification);
            return users;
        } catch (RepositoryException e) {
            logger.log(Level.INFO,"find by role",e);
            return List.of();
        }

    }

    @Override
    public List<User> findUserByStatus(UserStatus status) {

        FindUserByStatusSpecification specification = new FindUserByStatusSpecification(status);
        try {
            List<User> users = UserRepository.getInstance().findQuery(specification);
            return users;
        } catch (RepositoryException e) {
            logger.log(Level.INFO,"find by status",e);
            return List.of();
        }

    }

    @Override
    public List<User> findUserByUsername(String username) {
        FindUserByNameSpecification specification = new FindUserByNameSpecification(username);
        try {
            List<User> users = UserRepository.getInstance().findQuery(specification);
            return users;
        } catch (RepositoryException e) {
            logger.log(Level.INFO,"find by name",e);
            return List.of();
        }

    }

    @Override
    public boolean creditUser(User user, int sum) throws ServiceException {
        long startSum = user.getBalance();
        ChangeBalanceSpecification modifySpecification = new ChangeBalanceSpecification(startSum + sum, user.getUsername());
        try {
            return UserRepository.getInstance().updateQuery(modifySpecification) > 0;
        } catch (RepositoryException e) {
            throw new ServiceException();
        }
    }

    @Override
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

    @Override
    public void setUserPhoto(File file, String username) throws ServiceException {
        User user = findUserByUsername(username).get(0);
        FileManager fileManager=new FileManager();
        String extension=file.getName().substring(file.getName().lastIndexOf('.'));
        if(SupportedImagesFormat.isSupported(extension)) {
            File dest = fileManager.moveFileToUserDir(file, username, AVATAR_FILE_NAME + extension);
            user.setPhotoPath(PagePathProvider.USER_FILES + PagePathProvider.SEPARATOR +
                    user.getUsername() + PagePathProvider.SEPARATOR + dest.getName());
            ChangeUserSpecification specification = new ChangeUserSpecification(user);
            try {
                UserRepository.getInstance().updateQuery(specification);
            } catch (RepositoryException e) {
                throw new ServiceException();
            }
        }else {
            throw new ServiceException();
        }
    }

    @Override
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

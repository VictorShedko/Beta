package by.victor.beta.service;

import by.victor.beta.entity.Notification;
import by.victor.beta.entity.util.NotifyType;
import by.victor.beta.entity.User;

import java.util.List;

public interface INotifyService {
    List<Notification> findNotifies(String username) throws ServiceException;

    void addNotify(String text, User receiver, NotifyType type) throws ServiceException;
}

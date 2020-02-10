package by.victor.beta.service.impl;

import by.victor.beta.entity.Notification;
import by.victor.beta.entity.NotifyType;
import by.victor.beta.entity.User;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.NotifyRepository;
import by.victor.beta.repository.specification.impl.notifyspecification.AddNotifySpecification;
import by.victor.beta.repository.specification.impl.notifyspecification.FindNotifyByUsername;
import by.victor.beta.service.AbstractNotifyService;
import by.victor.beta.service.CleanerEntutyProvider;
import by.victor.beta.service.ServiceException;

import java.util.List;

public class NotifyService implements AbstractNotifyService {
    @Override
    public List<Notification> findNotifies(String username) throws ServiceException {
        FindNotifyByUsername specification = new FindNotifyByUsername(username);
        try {
            return NotifyRepository.getInstance().findQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public void addNotify(String text, User receiver, NotifyType type) throws ServiceException {
        CleanerEntutyProvider factory = new CleanerEntutyProvider();
        Notification notification = factory.getNotify(text, receiver, type);
        AddNotifySpecification specification = new AddNotifySpecification(notification);
        try {
            NotifyRepository.getInstance().updateQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException();
        }
    }

}

package by.victor.beta.service.impl;

import by.victor.beta.entity.*;
import by.victor.beta.entity.util.NotifyType;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.NotifyRepository;
import by.victor.beta.repository.specification.impl.notifyspecification.AddNotifySpecification;
import by.victor.beta.repository.specification.impl.notifyspecification.FindNotifyByUsername;
import by.victor.beta.service.INotifyService;
import by.victor.beta.service.CleanerEntityProvider;
import by.victor.beta.service.util.NotifyMessageBuilder;
import by.victor.beta.service.ServiceException;
import by.victor.beta.service.util.mail.MailServiceThread;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class NotifyService implements INotifyService {
    private static final Logger logger= LogManager.getLogger(NotifyService.class);
    private NotifyMessageBuilder messageBuilder=new NotifyMessageBuilder();
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
        CleanerEntityProvider factory = new CleanerEntityProvider();
        Notification notification = factory.getNotify(text, receiver, type);
        logger.log(Level.TRACE,"add notify :"+text+" to user :"+receiver.getUsername()+" type"+type);
        AddNotifySpecification specification = new AddNotifySpecification(notification);
        String emailText=messageBuilder.buildByPattern(messageBuilder.getNotifyValues(text),type);
        MailServiceThread.sendMessage(receiver,emailText);
        try {
            NotifyRepository.getInstance().updateQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException();
        }
    }


}

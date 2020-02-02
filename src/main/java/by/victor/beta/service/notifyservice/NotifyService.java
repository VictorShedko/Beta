package by.victor.beta.service.notifyservice;

import by.victor.beta.entity.Notify;
import by.victor.beta.entity.User;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.NotifyRepository;
import by.victor.beta.repository.specification.impl.notifyspecification.AddNotifySpecification;
import by.victor.beta.repository.specification.impl.notifyspecification.FindNotifyByUsername;
import by.victor.beta.service.CleanerFactory;
import by.victor.beta.service.ServiceException;

import java.util.List;

public class NotifyService {

    public List<Notify> findNotifies(String username) throws ServiceException {
        FindNotifyByUsername specification= new FindNotifyByUsername(username);
        try {
            return  NotifyRepository.getInstance().findQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }

    }

    public void addNotify(String text, User receiver) throws ServiceException {
        CleanerFactory factory=new CleanerFactory();
        Notify notify=factory.getNotify(text,receiver);
        AddNotifySpecification specification=new AddNotifySpecification(notify);
        try {
            NotifyRepository.getInstance().updateQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException();
        }
    }

}

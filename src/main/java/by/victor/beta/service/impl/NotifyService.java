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

    @Deprecated
    public void addNotify(NotifyType type, Entity... entity){
        switch (type){
            case ORDER_EXECUTION_START:{
                messageBuilder.orderExecutionStartMessage((User) entity[0],(User)entity[1],(Order) entity[3]);
            }break;
            case REGISTRATION:{
                messageBuilder.registrationMessage((User) entity[0]);
            }break;
            case ORDER_CREATED:{
                messageBuilder.orderCreateMessage((User) entity[0],(Order) entity[1]);
            }break;
            case ORDER_ACCEPTED:{
                messageBuilder.orderAcceptedMessage((User) entity[0],(User)entity[1],(Order) entity[3]);
            }break;
            case ORDER_NOT_CLAIMED:{
                messageBuilder.orderNotClaimedMessage((User) entity[0],(Order) entity[1]);
            }break;
            case ORDER_EXECUTOR_REFUSE:{
                messageBuilder.orderRefuseMessage((User) entity[0],(Order) entity[1]);
            }break;
            case ORDER_EXECUTION_FINISH_TO_EXECUTOR:{
                messageBuilder.orderExecutionFinishToExecutor((User) entity[0],(User)entity[1],(Order) entity[3]);

            }break;
            case ORDER_CANCEL_TO_CUSTOMER:{
                messageBuilder.orderCanceledMessageToCustomer((User) entity[0],(Order) entity[1]);
            }break;
            case ORDER_CANCEL_TO_EXECUTOR:{
                messageBuilder.orderCanceledMessageToExecutor((User) entity[0]);
            }break;
            case ORDER_EXECUTION_FINISH_TO_CUSTOMER:{
                messageBuilder.orderExecutionFinishToCustomer((User) entity[0],(User)entity[1],(Order) entity[3]);

            }break;
            case REGISTRATION_EMAIL_VERIFY_REQUEST:{
               // messageBuilder.buildEmailVerification();
            }break;
            case CREDIT_ACCOUNT:{
             //   messageBuilder.
            }break;
            case ADMIN_VALIDATION:{
            //    messageBuilder.
            }break;
            case EMAIL_CONFIRMED:{
             //   messageBuilder.
            }break;
            default:{

            }
        }

    }
}

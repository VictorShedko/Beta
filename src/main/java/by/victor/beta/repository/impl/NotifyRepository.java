package by.victor.beta.repository.impl;

import by.victor.beta.entity.Notification;
import by.victor.beta.repository.Repository;
import by.victor.beta.service.CleanerEntutyProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class NotifyRepository extends Repository<Notification> {

    private static AtomicBoolean created = new AtomicBoolean(false);
    private static NotifyRepository notifyRepository = null;
    private static final Logger logger = LogManager.getLogger(OrderRepository.class);


    private NotifyRepository() {

    }

    public static NotifyRepository getInstance() {
        if (created.compareAndSet(false, true)) {
            notifyRepository = new NotifyRepository();
        }
        return notifyRepository;
    }

    @Override
    protected Notification buildEntity(ResultSet resultSet, CleanerEntutyProvider factory) throws SQLException {
        return factory.getNotify(resultSet);
    }


}

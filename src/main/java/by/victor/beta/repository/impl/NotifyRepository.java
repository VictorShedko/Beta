package by.victor.beta.repository.impl;

import by.victor.beta.entity.Notification;
import by.victor.beta.repository.Repository;
import by.victor.beta.service.CleanerEntityProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class NotifyRepository extends Repository<Notification> {
    private static final NotifyRepository notifyRepository = new NotifyRepository();

    private NotifyRepository() {

    }

    public static NotifyRepository getInstance() {
        return notifyRepository;
    }

    @Override
    protected Notification buildEntity(ResultSet resultSet, CleanerEntityProvider factory) throws SQLException {
        return factory.getNotify(resultSet);
    }


}

package by.victor.beta.repository.impl;

import by.victor.beta.repository.Repository;
import by.victor.beta.service.CleanerEntutyProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeRepository extends Repository<Date> {
    private static AtomicBoolean created = new AtomicBoolean(false);
    private static TimeRepository timeRepository = null;
    private static final Logger logger = LogManager.getLogger(TimeRepository.class);


    private TimeRepository() {

    }

    public static TimeRepository getInstance() {
        if (created.compareAndSet(false, true)) {
            timeRepository = new TimeRepository();
        }
        return timeRepository;
    }

    @Override
    protected Date buildEntity(ResultSet resultSet, CleanerEntutyProvider factory) throws SQLException {
        return factory.getUtilDate(resultSet);
    }
}

package by.victor.beta.repository.impl;

import by.victor.beta.repository.Repository;
import by.victor.beta.service.CleanerEntityProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeRepository extends Repository<Date> {
    private static final TimeRepository timeRepository = new TimeRepository();

    private TimeRepository() {

    }

    public static TimeRepository getInstance() {
        return timeRepository;
    }

    @Override
    protected Date buildEntity(ResultSet resultSet, CleanerEntityProvider factory) throws SQLException {
        return factory.getUtilDate(resultSet);
    }
}

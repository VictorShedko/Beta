package by.victor.beta.repository.impl;

import by.victor.beta.entity.VerifyCode;
import by.victor.beta.repository.Repository;
import by.victor.beta.service.CleanerEntityProvider;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenRepository extends Repository<VerifyCode> {//todo singleton
    @Override
    protected VerifyCode buildEntity(ResultSet resultSet, CleanerEntityProvider factory) throws SQLException {
        return factory.getToken(resultSet);
    }
}

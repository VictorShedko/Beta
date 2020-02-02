package by.victor.beta.repository;

import by.victor.beta.entity.Document;
import by.victor.beta.entity.Entity;
import by.victor.beta.repository.specification.Specification;
import by.victor.beta.service.CleanerFactory;
import by.victor.beta.service.ServiceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> {
    abstract protected  T buildEntity(ResultSet resultSet,CleanerFactory factory) throws SQLException;

    public List<T> findQuery(Specification specification) throws RepositoryException {

        List<T> entities;
        try (Connection cn = ConnectionProvider.instance.occupyConnection().orElseThrow(RepositoryException::new)){

            try(PreparedStatement preparedStatement=specification.specify(cn)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    entities = new ArrayList<>();
                    CleanerFactory factory = new CleanerFactory();
                    while (resultSet.next()) {
                        T entity = buildEntity(resultSet,factory);
                        entities.add(entity);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();//todo
            throw new RepositoryException(e);
        }

        return entities;
    }

    public int updateQuery(Specification specification) throws RepositoryException {
        try (Connection cn = ConnectionProvider.instance.occupyConnection().orElseThrow(RepositoryException::new)) {
            return specification.specify(cn).executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException();
        }
    }

    public int createQuery(Specification specification) throws RepositoryException {
        try (Connection cn = ConnectionProvider.instance.occupyConnection().orElseThrow(RepositoryException::new)) {
            return specification.specify(cn).executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RepositoryException();
        }
    }
}

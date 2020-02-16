package by.victor.beta.repository;

import by.victor.beta.repository.specification.Specification;
import by.victor.beta.service.CleanerEntityProvider;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> {//todo singleton
    private static final Logger logger= LogManager.getLogger(Repository.class);
    abstract protected  T buildEntity(ResultSet resultSet, CleanerEntityProvider factory) throws SQLException;

    public List<T> findQuery(Specification specification) throws RepositoryException {

        List<T> entities;
        try (ProxyConfection cn = ConnectionPool.INSTANCE.occupyConnection().orElseThrow(()-> {
             return  new RepositoryException("cant get connetion");
        })){
            try(PreparedStatement preparedStatement=specification.specify(cn)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    entities = new ArrayList<>();
                    CleanerEntityProvider factory = new CleanerEntityProvider();
                    while (resultSet.next()) {
                        T entity = buildEntity(resultSet,factory);
                        entities.add(entity);
                    }
                }
            }
        } catch (SQLException e) {
          logger.log(Level.ERROR," Query ",e);
            throw new RepositoryException(e);
        }

        return entities;
    }

    public int updateQuery(Specification specification) throws RepositoryException {
        try (ProxyConfection cn = ConnectionPool.INSTANCE.occupyConnection().orElseThrow(RepositoryException::new)) {
            return specification.specify(cn).executeUpdate();


        } catch (SQLException e) {
            logger.log(Level.ERROR," Update ",e);
            throw new RepositoryException(e);
        }
    }

    public int createQuery(Specification specification) throws RepositoryException {
        try (ProxyConfection cn = ConnectionPool.INSTANCE.occupyConnection().orElseThrow(RepositoryException::new)) {
            return specification.specify(cn).executeUpdate();


        } catch (SQLException e) {
            logger.log(Level.ERROR," Create ",e);
            throw new RepositoryException();
        }
    }
}

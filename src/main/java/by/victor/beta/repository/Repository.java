package by.victor.beta.repository;

import by.victor.beta.repository.specification.Specification;
import by.victor.beta.service.CleanerEntityProvider;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Repository.
 *
 * @param <T> the entity the repository is working with
 */
public abstract class Repository<T> {
    private static final Logger logger= LogManager.getLogger(Repository.class);

    /**
     * Build entity t.
     *
     * @param resultSet the result set
     * @param factory   entity creator
     * @return entity
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
    abstract protected  T buildEntity(ResultSet resultSet, CleanerEntityProvider factory) throws SQLException, IOException;

    /**
     *get a list of entities from a database that meet the specification.
     *
     * @param specification the specification
     * @return the list
     * @throws RepositoryException the repository exception
     */
    public List<T> findQuery(Specification specification) throws RepositoryException {

        List<T> entities;
        try (ProxyConnection connection = ConnectionPool.INSTANCE.occupyConnection().orElseThrow(()-> {
             return  new RepositoryException("cant get connetion");
        })){
            try(PreparedStatement preparedStatement=specification.specify(connection)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    entities = new ArrayList<>();
                    CleanerEntityProvider factory = new CleanerEntityProvider();
                    while (resultSet.next()) {
                        T entity = buildEntity(resultSet,factory);
                        entities.add(entity);
                    }
                } catch (IOException e) {
                    logger.log(Level.ERROR," Query IO ex",e);
                    throw new RepositoryException(e);
                }
            }
        } catch (SQLException e) {
          logger.log(Level.ERROR," Query Sql ex ",e);
            throw new RepositoryException(e);
        }

        return entities;
    }

    /**
     * Update database and return affected rows amount.
     *
     * @param specification the specification
     * @return the int
     * @throws RepositoryException the repository exception
     */
    public int updateQuery(Specification specification) throws RepositoryException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.occupyConnection().orElseThrow(RepositoryException::new)) {
            return specification.specify(connection).executeUpdate();


        } catch (SQLException e) {
            logger.log(Level.ERROR," Update ",e);
            throw new RepositoryException(e);
        }
    }


}

package by.victor.beta.logic.reposytory;

import by.victor.beta.logic.reposytory.dbconnection.ConnectionProvider;
import by.victor.beta.logic.reposytory.dbconnection.NoFreeConnectionException;
import by.victor.beta.logic.reposytory.specification.Specification;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public enum Repository {
    instance;
    private static final Logger logger = LogManager.getLogger(Repository.class);


    Repository() {

    }

    public void query(Specification specification) throws RepositoryException {
        Connection cn= null;
        try {
            cn = ConnectionProvider.instance.occupyConnection().orElseThrow(NoFreeConnectionException::new);
            specification.execute(cn);
        } catch (NoFreeConnectionException | SQLException e) {
            e.printStackTrace();
            throw new RepositoryException(e);
        }

    }

}

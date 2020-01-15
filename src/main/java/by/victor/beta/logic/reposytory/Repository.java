package by.victor.beta.logic.reposytory;

import by.victor.beta.logic.dbconection.ConnectionProvider;
import by.victor.beta.logic.dbconection.NoFreeConnectionException;
import by.victor.beta.logic.reposytory.specification.Specification;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public enum Repository {
    instance;
    private static final Logger logger = LogManager.getLogger(Repository.class);


    Repository() {

    }

    public void Query(Specification specification) throws NoFreeConnectionException, SQLException {
        Connection cn= ConnectionProvider.instance.occupyConnection().orElseThrow(NoFreeConnectionException::new);
        specification.execute(cn);
    }

}

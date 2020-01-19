package by.victor.beta.logic.reposytory.specification;

import by.victor.beta.logic.reposytory.RepositoryException;

import java.sql.Connection;
import java.sql.SQLException;

public interface Specification {
    void execute(Connection connection) throws SQLException, RepositoryException;

}

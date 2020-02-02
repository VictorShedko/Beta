package by.victor.beta.repository.specification;

import by.victor.beta.repository.RepositoryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Specification {
    PreparedStatement specify(Connection connection) throws SQLException, RepositoryException;

}

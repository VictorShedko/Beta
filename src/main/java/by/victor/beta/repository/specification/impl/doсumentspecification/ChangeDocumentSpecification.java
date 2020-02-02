package by.victor.beta.repository.specification.impl.doсumentspecification;

import by.victor.beta.entity.Document;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeDocumentSpecification implements Specification {
    private Document document;

    private String CHANGE_DOCUMENT = "UPDATE document SET document.file=?," +
            "document.admin_check_id=?,document.iddocument=?,document.user_id=?" +
            "WHERE document.iddocument=?";


    public ChangeDocumentSpecification( Document document) {
        this.document=document;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException, RepositoryException {
        PreparedStatement statement = connection.prepareStatement(CHANGE_DOCUMENT);
        statement.setString(1,document.getFile());
        statement.setLong(2,document.getAdminId());
        statement.setLong(3, document.getId());
        statement.setLong(4, document.getUserId());
        statement.setLong(5,  document.getId());
        return statement;
    }
}

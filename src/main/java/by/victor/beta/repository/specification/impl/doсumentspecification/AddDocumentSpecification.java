package by.victor.beta.repository.specification.impl.doсumentspecification;

import by.victor.beta.entity.Document;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDocumentSpecification implements Specification {
    private static final String INSERT_INTO_DOCUMENT = "INSERT INTO document (file,user_id) VALUES (?,?)";

    private Document document;

    public AddDocumentSpecification(Document document) {
        this.document = document;
    }

    @Override
    public PreparedStatement specify(Connection connection) throws SQLException, RepositoryException {
        PreparedStatement ps = connection.prepareStatement(INSERT_INTO_DOCUMENT);
        ps.setString(1,document.getFile());
        ps.setLong(2,document.getUserId());
        return ps;
    }



}

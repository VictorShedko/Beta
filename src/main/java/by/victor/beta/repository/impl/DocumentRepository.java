package by.victor.beta.repository.impl;

import by.victor.beta.entity.Document;
import by.victor.beta.repository.ConnectionProvider;
import by.victor.beta.repository.Repository;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.specification.Specification;
import by.victor.beta.service.CleanerFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class DocumentRepository extends Repository<Document> {
    private static AtomicBoolean created = new AtomicBoolean(false);
    private static DocumentRepository documentRepository;
    private static final Logger logger = LogManager.getLogger(OrderRepository.class);


    private DocumentRepository() {

    }

    public static DocumentRepository getInstance() {
        if (created.compareAndSet(false, true)) {
            documentRepository = new DocumentRepository();
        }
        return documentRepository;
    }


    @Override
    protected Document buildEntity(ResultSet resultSet,CleanerFactory factory) throws SQLException {
        return factory.getDocument(resultSet);
    }
}

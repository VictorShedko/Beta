package by.victor.beta.repository.impl;

import by.victor.beta.entity.Document;
import by.victor.beta.repository.Repository;
import by.victor.beta.service.CleanerEntityProvider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

public class DocumentRepository extends Repository<Document> {
    private static DocumentRepository documentRepository=new DocumentRepository();



    private DocumentRepository() {

    }

    public static DocumentRepository getInstance() {
        return documentRepository;
    }


    @Override
    protected Document buildEntity(ResultSet resultSet, CleanerEntityProvider factory) throws SQLException, IOException {
        return factory.getDocument(resultSet);
    }
}

package by.victor.beta.service;

import by.victor.beta.entity.Document;
import by.victor.beta.entity.User;

import java.io.File;
import java.util.List;

public interface AbstractDocumentService {
    void addDocument(User user, File file);

    List<Document> getUserDocuments(String username) throws ServiceException;

    void checkDocument(User admin, Document document) throws ServiceException;

    Document findDocumentById(int id) throws ServiceException;
}

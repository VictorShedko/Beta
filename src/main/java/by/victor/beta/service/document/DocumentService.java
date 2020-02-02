package by.victor.beta.service.document;

import by.victor.beta.entity.Document;
import by.victor.beta.entity.User;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.DocumentRepository;
import by.victor.beta.repository.specification.impl.doсumentspecification.AddDocumentSpecification;
import by.victor.beta.repository.specification.impl.doсumentspecification.ChangeDocumentSpecification;
import by.victor.beta.repository.specification.impl.doсumentspecification.FindDocumentByUsernameSpecification;
import by.victor.beta.service.CleanerFactory;
import by.victor.beta.service.ServiceException;

import java.io.File;
import java.util.List;

public class DocumentService {
    public void addDocument(User user, File file){
        CleanerFactory factory=new CleanerFactory();
        Document document=factory.getDocument(user,file.getAbsolutePath());
        AddDocumentSpecification specification=new AddDocumentSpecification(document);
        try {
            DocumentRepository.getInstance().createQuery(specification);
        } catch (RepositoryException e) {
            e.printStackTrace();//todo sdcs
        }
    }

    public List<Document> getUserDocuments(String username) throws ServiceException {

        FindDocumentByUsernameSpecification specification=new FindDocumentByUsernameSpecification(username);
        try {
            return DocumentRepository.getInstance().findQuery(specification);
        } catch (RepositoryException e) {
            e.printStackTrace();//todo sdcs
            throw new ServiceException();
        }
    }

    public void checkDocument(User admin,Document document){
        document.setAdminId(admin.getId());
        document.setAdminName(admin.getUsername());
        ChangeDocumentSpecification specification=new ChangeDocumentSpecification(document);
        try {
            DocumentRepository.getInstance().updateQuery(specification);
        } catch (RepositoryException e) {
            e.printStackTrace();//todo sdcs
        }
    }

}

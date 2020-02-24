package by.victor.beta.service.impl;

import by.victor.beta.entity.Document;
import by.victor.beta.entity.User;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.DocumentRepository;
import by.victor.beta.repository.specification.impl.doсumentspecification.AddDocumentSpecification;
import by.victor.beta.repository.specification.impl.doсumentspecification.ChangeDocumentSpecification;
import by.victor.beta.repository.specification.impl.doсumentspecification.FindDocumentByIdSpecification;
import by.victor.beta.repository.specification.impl.doсumentspecification.FindDocumentByUsernameSpecification;
import by.victor.beta.service.IDocumentService;
import by.victor.beta.service.CleanerEntityProvider;
import by.victor.beta.service.util.FileManager;
import by.victor.beta.service.ServiceException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DocumentService implements IDocumentService {

    private CleanerEntityProvider factory=new CleanerEntityProvider();
    @Override
    public void addDocument(User user, File file) throws ServiceException {
        String uuid=FileManager.INSTANCE.generateUUIDNameWithSameExtension(file);
        File movedFile= null;
        try {
            movedFile = FileManager.INSTANCE.moveFileToUserDir(file,user.getUsername(),uuid);
        } catch (IOException e) {
            e.printStackTrace();//todo
            throw new ServiceException(e);
        }
        Document document=factory.getDocument(user,movedFile.getName());
        AddDocumentSpecification specification=new AddDocumentSpecification(document);
        try {
            DocumentRepository.getInstance().createQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
    @Override
    public List<Document> getUserDocuments(String username) throws ServiceException {

        FindDocumentByUsernameSpecification specification=new FindDocumentByUsernameSpecification(username);
        try {
            List<Document> documents=DocumentRepository.getInstance().findQuery(specification);
            return documents;
        } catch (RepositoryException e) {
            e.printStackTrace();//todo sdcs
            throw new ServiceException(e);
        }
    }
    @Override
    public void checkDocument(User admin, Document document) throws ServiceException {
        document.setAdminId(admin.getId());
        document.setAdminName(admin.getUsername());
        ChangeDocumentSpecification specification=new ChangeDocumentSpecification(document);
        try {
            DocumentRepository.getInstance().updateQuery(specification);
        } catch (RepositoryException e) {
            e.printStackTrace();//todo sdcs
            throw new ServiceException(e);
        }
    }
    @Override
    public Document findDocumentById(int id) throws ServiceException {

        FindDocumentByIdSpecification specification=new FindDocumentByIdSpecification(id);
        try {
            List<Document> documents=DocumentRepository.getInstance().findQuery(specification);
            if (documents.size()>0) {
                return documents.get(0);//todo или проверка
            }else {
                throw new ServiceException();//todo const
            }
        } catch (RepositoryException e) {
           throw new ServiceException(e);
        }
    }

}

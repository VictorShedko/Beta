package by.victor.beta.service.impl;

import by.victor.beta.entity.*;
import by.victor.beta.entity.VerifyCode;
import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.VerifyCodeRepository;
import by.victor.beta.repository.specification.impl.verifycode.AddVerifyCodeSpecification;
import by.victor.beta.repository.specification.impl.verifycode.FindVerifyCodeByUuidSpecification;
import by.victor.beta.service.IVerifyCodeService;
import by.victor.beta.service.ServiceException;
import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class VerifyCodeService implements IVerifyCodeService {
    private static final Logger logger= LogManager.getLogger(VerifyCodeService.class);
    private VerifyCodeRepository repository = new VerifyCodeRepository();

    @Override
    public void addToken(VerifyCode verifyCode) throws ServiceException {
        AddVerifyCodeSpecification specification = new AddVerifyCodeSpecification(verifyCode);
        logger.log(Level.TRACE,"add verify code"+verifyCode);
        try {
            repository.createQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean isValidToken(VerifyCode verifyCode, User user) {
        Date now=new Date();
        return (now.getTime()- verifyCode.getTime().getTime()< TimeUnit.MINUTES.toMillis(15))&&
                (user.getId()==verifyCode.getUserId());//todo to properties
    }

    public List<VerifyCode> getTokenByUuid(String uuid) throws ServiceException {
        FindVerifyCodeByUuidSpecification specification = new FindVerifyCodeByUuidSpecification(uuid);
        List<VerifyCode> verifyCodes;
        try {
            verifyCodes =repository.findQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        return verifyCodes;
    }

    @Override
    public Optional<VerifyCode> getSingleTokenByUuid(String uuid) throws ServiceException {
        FindVerifyCodeByUuidSpecification specification = new FindVerifyCodeByUuidSpecification(uuid);
        List<VerifyCode> verifyCodes;
        try {
            verifyCodes =repository.findQuery(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
        if(verifyCodes.size()==0){
            return Optional.empty();

        }else {
            return Optional.of(verifyCodes.get(0));
        }

    }
}

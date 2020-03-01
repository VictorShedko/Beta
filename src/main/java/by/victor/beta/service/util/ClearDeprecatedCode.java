package by.victor.beta.service.util;

import by.victor.beta.repository.RepositoryException;
import by.victor.beta.repository.impl.VerifyCodeRepository;
import by.victor.beta.repository.specification.Specification;
import by.victor.beta.repository.specification.impl.verifycode.ClearCodeSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Remove from bd not valid verify code.
 */
public enum  ClearDeprecatedCode {

    INSTANCE;
    private static final Logger logger= LogManager.getLogger(ClearDeprecatedCode.class);
    private final Timer timer=new Timer();
    private final long DELAY= TimeUnit.MINUTES.toMillis(5);
    private Specification clearDeprecatedCodeSpecification =new ClearCodeSpecification();

    ClearDeprecatedCode()
    {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                deleteCodes();
            }
        }, DELAY);

    }

    /**
     * call update method in repository
     */
    public void deleteCodes(){
        try {
            VerifyCodeRepository.getINSTANCE().updateQuery(clearDeprecatedCodeSpecification);
            logger.log(Level.ERROR,"delete deprecated order");
        } catch (RepositoryException e) {
            logger.log(Level.ERROR,"delete deprecated code error ",e);
        }

    }

}

package by.victor.beta.service.util;

import by.victor.beta.command.ApplicationParameter;
import by.victor.beta.command.PagePath;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public enum  FileManager {
    INSTANCE;
    private static final Logger logger= LogManager.getLogger(FileManager.class);
    private  ServletContext applicationContext;
    private static final ReentrantLock fileOperationLock=new ReentrantLock();
    public File moveFileToUserDir(File file, String user,String fileName){
        fileOperationLock.lock();
        try {


            File tmpDir = new File(file.getParent());
            File userDirectory = new File(tmpDir.getParent() + PagePath.SEPARATOR +
                    PagePath.USER_FILES + PagePath.SEPARATOR + user);
            if (!userDirectory.isDirectory()) {
                if (!userDirectory.mkdir()) {
                    logger.log(Level.ERROR, "folder create fail");
                } else {
                    logger.log(Level.DEBUG, "folder created :" + userDirectory.getName());
                }
            }
            File destination = new File(userDirectory.getAbsoluteFile() + PagePath.SEPARATOR + fileName);
            file.renameTo(destination);
            return destination;
        }finally {
            fileOperationLock.unlock();
        }
    }

    public File moveFileToTempUserDir(String fileName){
        fileOperationLock.lock();
        try {
            File realFile = new File(ApplicationParameter.FILE_DIRECTORY + PagePath.SEPARATOR + fileName);

            File tmpFile = new File(applicationContext.getRealPath("") + PagePath.SEPARATOR + fileName);
            File tmpDir = tmpFile.getParentFile();
            if (!tmpDir.isDirectory()) {
                if (!tmpDir.mkdirs()) {
                    logger.log(Level.ERROR, "tmp dir create error " + tmpDir.getName());
                }
            }

            realFile.renameTo(tmpFile);
            return realFile;
        }finally {
            fileOperationLock.unlock();
        }
    }

    public String generateUUIDNameWithSameExtension(File file){
        UUID uuid=UUID.randomUUID();
        StringBuilder name=new StringBuilder(uuid.toString());
        String oldFileName=file.getName();
        name.append(getExtension(oldFileName));
        return name.toString();
    }

    public String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.'));
    }

    public void setApplicationContext(ServletContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}

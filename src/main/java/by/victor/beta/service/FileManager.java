package by.victor.beta.service;

import by.victor.beta.command.AttributeNameProvider;
import by.victor.beta.command.PagePathProvider;
import by.victor.beta.entity.User;

import java.io.File;

public class FileManager {


    public File moveFileToUserDir(File file, String user,String fileName){
        File tmpDir=new File(file.getParent());
        File userDirectory=new File(tmpDir.getParent()+PagePathProvider.SEPARATOR+
                PagePathProvider.USER_FILES +PagePathProvider.SEPARATOR+user);
        if (!userDirectory.isDirectory()){
            userDirectory.mkdir();
        }
        File destination=new File(userDirectory.getAbsoluteFile()+PagePathProvider.SEPARATOR+fileName);
        file.renameTo(destination);
        return destination;
    }
}

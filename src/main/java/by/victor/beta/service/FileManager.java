package by.victor.beta.service;

import by.victor.beta.command.AttributeNameProvider;
import by.victor.beta.command.PagePathProvider;
import by.victor.beta.entity.User;

import java.io.File;
import java.util.UUID;

public class FileManager {//todo absolute

    public File moveFileToUserDir(File file, String user,String fileName){
        File tmpDir=new File(file.getParent());
        File userDirectory=new File(tmpDir.getParent()+PagePathProvider.SEPARATOR+
                PagePathProvider.USER_FILES +PagePathProvider.SEPARATOR+user);
        if (!userDirectory.isDirectory()){
            if(!userDirectory.mkdir()){
                //todo throw ex
            }
        }
        File destination=new File(userDirectory.getAbsoluteFile()+PagePathProvider.SEPARATOR+fileName);
        file.renameTo(destination);
        return destination;
    }

    public String generateUUIDNameWithSameExtension(File file){
        UUID uuid=UUID.randomUUID();
        StringBuilder name=new StringBuilder(uuid.toString());
        String oldFileName=file.getName();
        name.append(oldFileName.substring(oldFileName.lastIndexOf('.')));
        return name.toString();
    }

}

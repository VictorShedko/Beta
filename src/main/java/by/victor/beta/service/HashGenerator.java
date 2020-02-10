package by.victor.beta.service;

import by.victor.beta.entity.User;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class HashGenerator {
    private static final byte[] salt="badSalt".getBytes();
    public String getHash(String password,User user) throws ServiceException {
        byte[] userSalt=getUserSalt(user);
        byte[] globalSalt=getGlobalSalt();
        byte[] resultSalt=new byte[userSalt.length + globalSalt.length];
        System.arraycopy(userSalt, 0, resultSalt, 0, userSalt.length);
        System.arraycopy(globalSalt, 0, resultSalt, userSalt.length, globalSalt.length);
        KeySpec spec = new PBEKeySpec(password.toCharArray(),resultSalt , 32, 128);
        SecretKeyFactory factory;
        try {
             factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return new String(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServiceException();
        }


    }
    private byte[] getNewSalt(){
        SecureRandom random = new SecureRandom();
        byte[]salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] getGlobalSalt(){
        ResourceBundle hash = ResourceBundle.getBundle("hash");
        String globalSalt=hash.getString("globalsalt");
        byte[] salt=globalSalt.getBytes();
        return salt;
    }

    private byte[] getUserSalt(User user){
        return salt;
    }
}

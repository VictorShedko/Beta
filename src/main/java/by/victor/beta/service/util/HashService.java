package by.victor.beta.service.util;

import by.victor.beta.entity.User;
import by.victor.beta.service.ServiceException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;

public enum HashService {
    INSTANCE;

    /**
     * Get hash byte [ ].
     * To hash generation use individual user salt and global salt
     *
     * @param password the password
     * @param userSalt the user salt
     * @return the byte [ ]
     * @throws ServiceException the service exception
     */
    private static final String GLOBAL_SALT_KEY="globalsalt";
    private static final String HASH_PROPERTIES_FILENAME="C:\\Users\\ACER\\Documents\\Beta\\src\\main\\resources\\hash.properties";
    public byte[] getHash(String password, byte[] userSalt) throws ServiceException {
        byte[] globalSalt=getGlobalSalt();
        byte[] resultSalt=new byte[userSalt.length + globalSalt.length];
        System.arraycopy(userSalt, 0, resultSalt, 0, userSalt.length);
        System.arraycopy(globalSalt, 0, resultSalt, userSalt.length, globalSalt.length);
        KeySpec spec = new PBEKeySpec(password.toCharArray(),resultSalt , 32, 128);
        SecretKeyFactory factory;
        try {
             factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return hash;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServiceException(e);
        }


    }

    /**
     * generate new salt using secure random .
     *
     * @return the byte [ ]
     */
    public byte[] getNewSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] getGlobalSalt(){
        File file = new File(HASH_PROPERTIES_FILENAME);
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new IllegalStateException();//todo
        }
        String globalSalt=properties.getProperty(GLOBAL_SALT_KEY);
        byte[] salt=globalSalt.getBytes();
        return salt;
    }

    /**
     * Is right password boolean.
     *
     * @param user     the user
     * @param password the password
     * @return the boolean
     * @throws ServiceException the service exception
     */
    public boolean isRightPassword(User user,String password) throws ServiceException {
        return Arrays.equals(user.getPassword(),getHash(password,user.getSalt()));
    }
}

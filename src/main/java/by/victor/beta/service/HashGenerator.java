package by.victor.beta.service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class HashGenerator {
    private static final byte[] salt="badSalt".getBytes();
    public String getHash(String password) throws ServiceException {

        KeySpec spec = new PBEKeySpec(password.toCharArray(),salt , 32, 128);
        SecretKeyFactory factory;
        try {
             factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return new String(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServiceException();
        }


    }
}

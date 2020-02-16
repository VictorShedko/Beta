package by.victor.beta.service;

import by.victor.beta.entity.VerifyCode;

import java.util.Optional;

public interface IVerifyCodeService {
    void addToken(VerifyCode verifyCode) throws ServiceException;

    boolean isValidToken(VerifyCode verifyCode);

    Optional<VerifyCode> getSingleTokenByUuid(String uuid) throws ServiceException;
}

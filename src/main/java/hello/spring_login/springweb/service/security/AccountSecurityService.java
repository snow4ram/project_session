package hello.spring_login.springweb.service.security;


import hello.spring_login.springweb.application.request.LoginRequest;
import hello.spring_login.springweb.entity.User;
import hello.spring_login.springweb.repository.JpaUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hello.spring_login.springweb.entity.ExceptionInformation.*;

@Slf4j
@Service
public class AccountSecurityService {


    private final JpaUserRepository jpaUserRepository;


    private static final String DUPLICATE_ID = "아이디가 중복됩니다.";

    @Autowired
    public AccountSecurityService(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Transactional(readOnly = true)
    public User userValidateCredentials(final LoginRequest request) {
        return jpaUserRepository.findByUseridAndPassword(request.getUserid() , request.getPassword())
                .stream()
                .filter(identityVerificationCompleted -> {
                    if (request.getPassword().isBlank() || request.getPassword() == null) {
                        throw new RuntimeException(LOGIN_ERROR.getExceptionMessage());
                    }
                    return identityVerificationCompleted.getPassword().equals(request.getPassword());
                }).findAny().orElseThrow(() -> new RuntimeException(LOGIN_ERROR.getExceptionMessage()));
    }


    @Transactional(readOnly = true)
    public boolean checkForDuplicateUserId(final String signUpRequestUserId) {
        return jpaUserRepository.findByUserid(signUpRequestUserId)
                .stream()
                .anyMatch(duplicateUserId ->
                    duplicateUserId.getUserid().equals(signUpRequestUserId));
    }

}

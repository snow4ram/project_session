package hello.spring_login.springweb.service.security;


import hello.spring_login.springweb.application.request.LoginRequest;
import hello.spring_login.springweb.entity.ExceptionInformation;
import hello.spring_login.springweb.entity.User;
import hello.spring_login.springweb.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hello.spring_login.springweb.entity.ExceptionInformation.*;

@Slf4j
@Service
@Transactional
public class AccountSecurityService {


    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public AccountSecurityService(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }


    /*
    * user service login -> user 로그인 한 정보를 security 정보를 전달하고
    * 그것을 security에 정보를 받아서 repository에 값을 가져와 아이디 , 비밀번호를 비교 하는 로직.
    * 사용자에 id 값을 찾아오고 난 뒤에 비밀 번호도 같이 체킹
    * */
    public User userValidateCredentials(final LoginRequest request) {
        return jpaUserRepository.findByUserid(request.getUserid())
                .stream()
                .filter(identityVerificationCompleted -> {
                    if (request.getPassword().isBlank() || request.getPassword() == null) {
                        throw new RuntimeException();
                    }
                    return identityVerificationCompleted.getPassword().equals(request.getPassword());
                }).findAny().orElseThrow(() -> new RuntimeException(LOGIN_ERROR.getExceptionMessage()));
    }


}

package hello.spring_login.springweb.service;

import hello.spring_login.springweb.application.request.LoginRequest;
import hello.spring_login.springweb.application.request.SignUpRequest;
import hello.spring_login.springweb.dto.LoginDTO;
import hello.spring_login.springweb.dto.LoginDTOMapper;
import hello.spring_login.springweb.entity.User;
import hello.spring_login.springweb.exception.LoginRuntimeException;
import hello.spring_login.springweb.repository.JpaUserRepository;
import hello.spring_login.springweb.service.security.AccountSecurityService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {


    @Autowired
    private final JpaUserRepository jpaUserRepository;

    @Autowired
    private final AccountSecurityService accountSecurityService;


    @Autowired
    private final LoginDTOMapper dtoMapper;

    //private final String uuid = UUID.randomUUID().toString();

    private static final String LOGOUT_KEY = "login_key";

    @Override
    public LoginDTO signUp(final SignUpRequest signUpRequest, final HttpSession session) {

        boolean checkUserInformation = accountSecurityService.checkForDuplicateUserId(signUpRequest.getUserid());

        log.info("adminCheck = {}" , checkUserInformation);

        log.info("signUpRequest = {}" , signUpRequest.getUserid());


        if (checkUserInformation) {
            throw new RuntimeException("이미 사용한 아이디 입니다.");
        }

        User user = User.builder()
                .userid(signUpRequest.getUserid())
                .password(signUpRequest.getPassword())
                .uniqueID(LOGOUT_KEY).build();


        session.setAttribute(LOGOUT_KEY , user.getUserid());

        User save = jpaUserRepository.save(user);

        return dtoMapper.apply(save);
    }

    @Override
    public LoginDTO login(final LoginRequest loginRequest , final HttpSession session) {

        User user = accountSecurityService.userValidateCredentials(loginRequest);

        if (user != null) {
            session.setAttribute(user.getUniqueID() , user);
        }else {
            throw new LoginRuntimeException("사용자의 정보를 찾을수없습니다.");
        }

        return dtoMapper.apply(user);
    }

    @Override
    public void logout(final HttpSession httpSession) {
        httpSession.removeAttribute(LOGOUT_KEY);

    }
}

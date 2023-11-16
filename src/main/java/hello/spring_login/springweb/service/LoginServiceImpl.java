package hello.spring_login.springweb.service;

import hello.spring_login.springweb.application.request.LoginRequest;
import hello.spring_login.springweb.application.request.SignUpRequest;
import hello.spring_login.springweb.dto.LoginDTO;
import hello.spring_login.springweb.dto.LoginDTOMapper;
import hello.spring_login.springweb.entity.User;
import hello.spring_login.springweb.repository.JpaUserRepository;
import hello.spring_login.springweb.service.security.AccountSecurityService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


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

    private final String uuid = UUID.randomUUID().toString();

    @Override
    public LoginDTO signUp(final SignUpRequest signUpRequest, final HttpSession session) {

        User user = User.builder()
                .userid(signUpRequest.getUserid())
                .password(signUpRequest.getPassword())
                .uniqueID(uuid).build();


        session.setAttribute(uuid , user.getUserid());

        User save = jpaUserRepository.save(user);

        return dtoMapper.apply(save);
    }

    @Override
    public LoginDTO login(final LoginRequest loginRequest , final HttpSession session) {

        User user = accountSecurityService.userValidateCredentials(loginRequest);

        if (user != null) {
            session.setAttribute(user.getUniqueID() , user);
        }else {
            throw new RuntimeException("사용자 정보를 찾을수 없습니다.");
        }

        return dtoMapper.apply(user);
    }

    @Override
    public void logout(final HttpSession httpSession) {
        httpSession.invalidate();

    }
}

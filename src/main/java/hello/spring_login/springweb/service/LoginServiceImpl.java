package hello.spring_login.springweb.service;

import hello.spring_login.springweb.application.request.LoginRequest;
import hello.spring_login.springweb.entity.User;
import hello.spring_login.springweb.repository.JpaUserRepository;
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

    private final String uuid = UUID.randomUUID().toString();

    @Override
    public void signUp(final LoginRequest loginRequest, final HttpSession session) {

        User user = User.builder()
                .userid(loginRequest.getUserid())
                .password(loginRequest.getPassword())
                .uniqueID(uuid).build();


        session.setAttribute(uuid , user);

        jpaUserRepository.save(user);

    }

    @Override
    public void login(final LoginRequest loginRequest , final HttpSession session) {

    }

    @Override
    public void logout(final HttpSession httpSession) {

    }
}

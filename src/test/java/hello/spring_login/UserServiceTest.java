package hello.spring_login;


import hello.spring_login.springweb.application.request.LoginRequest;
import hello.spring_login.springweb.dto.LoginDTO;
import hello.spring_login.springweb.entity.User;
import hello.spring_login.springweb.repository.JpaUserRepository;
import hello.spring_login.springweb.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Autowired
    JpaUserRepository jpaUserRepository;


    @Autowired
    LoginService loginService;


    @Test
    @DisplayName("사용자 회원 가입")
    public void signUp() {
        //give
        LoginRequest signUp = new LoginRequest("", "password 3");

        MockHttpSession httpSession = new MockHttpSession();

        loginService.signUp(signUp , httpSession);

        //when
        User findUser = jpaUserRepository.findById(1L).orElseThrow();


        //then
        Assertions.assertThat(findUser.getUserid()).isEqualTo(signUp.getUserid());

        Assertions.assertThat(findUser.getPassword()).isEqualTo(signUp.getPassword());

        Assertions.assertThat(findUser).isNotNull();

        log.info("session = {}" , httpSession.getAttribute(findUser.getUniqueID()));
    }


    @Test
    @DisplayName("공백 문자 허용하지 않음")
    void id() {
    }

    @Test
    @DisplayName("")
    void password() {
    }

    @AfterEach
    public void userInformationDeleteAll() {

        jpaUserRepository.deleteAll();

    }
}



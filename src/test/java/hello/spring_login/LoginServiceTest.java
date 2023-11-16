package hello.spring_login;


import hello.spring_login.springweb.application.request.LoginRequest;
import hello.spring_login.springweb.entity.User;
import hello.spring_login.springweb.repository.JpaUserRepository;
import hello.spring_login.springweb.service.LoginServiceImpl;
import hello.spring_login.springweb.service.security.AccountSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    JpaUserRepository jpaUserRepository;

    @Autowired
    LoginServiceImpl loginService;

    @Autowired
    AccountSecurityService accountSecurityService;

    @Mock
    public MockHttpSession httpSession;

    @Test
    @DisplayName("로그인")
    void login() {

        //give
        LoginRequest request = new LoginRequest("userId2", "password2");


        loginService.signUp(request, httpSession);

        //when
        User user = jpaUserRepository.findByUserid("userId2").orElseThrow();


        //then
        Assertions.assertNotNull(user);

        Assertions.assertEquals(user.getUserid() , request.getUserid());

    }


    @Test
    @DisplayName("로그인")
    void Check_login_ID() {

        //giv


        User user = accountSecurityService.userValidateCredentials(new LoginRequest("userId", "asda"));

        log.info("user = {}" , user);
    }


//    @AfterEach
//    public void deleteAll() {
//        jpaUserRepository.deleteAll();
//
//    }



}

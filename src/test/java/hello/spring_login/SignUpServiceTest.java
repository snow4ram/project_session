package hello.spring_login;


import hello.spring_login.springweb.application.request.SignUpRequest;
import hello.spring_login.springweb.dto.LoginDTO;
import hello.spring_login.springweb.entity.User;
import hello.spring_login.springweb.repository.JpaUserRepository;
import hello.spring_login.springweb.service.LoginService;
import hello.spring_login.springweb.service.LoginServiceImpl;
import hello.spring_login.springweb.service.security.AccountSecurityService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SignUpServiceTest {

    @Autowired
    JpaUserRepository jpaUserRepository;

    @Autowired
    LoginService loginService;

    @Autowired
    AccountSecurityService accountSecurityService;

    @Mock
    MockHttpSession httpSession;

    @Autowired
    SignUp signUp;

    @Test
    @DisplayName("회원가입")
    void SignUp() {

        //give
        LoginDTO loginDTO = loginService.signUp(new SignUpRequest("user", "user"), httpSession);


        //when
        User findByUser = jpaUserRepository.findById(2L).orElseThrow();

        
        //then
        Assertions.assertThat(loginDTO.userid()).isEqualTo(findByUser.getUserid());

        Assertions.assertThat(findByUser).isNotNull();

    }


    //회원 가입 아이디 중복 확인

    @Test
    @DisplayName("회원가입시 등록된 아이디의 중복성을 확인하세요.")
    public void Verify_the_uniqueness_of_the_registered_ID_during_member_sign_up (){

        //give
        User user = signUp.signUp(new SignUpRequest("No Duplicate ID", "duplicatePassword"), httpSession);

        Assertions.assertThat(user).isNotNull();

    }

    @TestConfiguration
    static class TestBox  {
        @Autowired
        private AccountSecurityService accountSecurityService;

        @Autowired
        private JpaUserRepository jpaUserRepository;

        @Bean
        SignUp signUp (){
            return new SignUp(accountSecurityService , jpaUserRepository );
        }
    }

    private static final String LOGOUT_KEY = "key";

    static class SignUp {

        AccountSecurityService service;

        JpaUserRepository repository;

        public SignUp(AccountSecurityService service, JpaUserRepository repository) {
            this.service = service;
            this.repository = repository;
        }

        @Transactional
        public User signUp(final SignUpRequest signUpRequest, final HttpSession session) {

            boolean checkUserInformation = service.checkForDuplicateUserId(signUpRequest.getUserid());

            log.info("adminCheck = {}" , checkUserInformation);

            if (checkUserInformation) {
                throw new RuntimeException("이미 사용한 아이디 입니다.");
            }

            User user = User.builder()
                    .userid(signUpRequest.getUserid())
                    .password(signUpRequest.getPassword())
                    .uniqueID(LOGOUT_KEY).build();

            session.setAttribute(LOGOUT_KEY , user.getUserid());

            return repository.save(user);
        }
    }





    //회원가입 아이디 공백 입력, 미 입력



    //회원가입 비밀번호 공백 입력
}

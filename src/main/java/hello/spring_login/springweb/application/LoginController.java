package hello.spring_login.springweb.application;


import hello.spring_login.springweb.application.request.LoginRequest;
import hello.spring_login.springweb.application.request.SignUpRequest;
import hello.spring_login.springweb.application.response.LoginResponse;
import hello.spring_login.springweb.dto.LoginDTO;
import hello.spring_login.springweb.service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private final LoginService loginService;

    @PostMapping("/sign-up")
    public ResponseEntity<LoginResponse> signUpForm(@RequestBody final SignUpRequest signUpRequest ,final HttpSession session) {

        LoginDTO loginDTO = loginService.signUp(signUpRequest, session);

        LoginResponse loginResponse = new LoginResponse(loginDTO);

        //200
        return ResponseEntity.ok().body(loginResponse);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginForm(@RequestBody final LoginRequest loginRequest ,final HttpSession session) {

        LoginDTO loginDTO = loginService.login(loginRequest, session);

        LoginResponse loginResponse = new LoginResponse(loginDTO);

        //200
        return ResponseEntity.ok().body(loginResponse);
    }

}

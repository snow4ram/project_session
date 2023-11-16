package hello.spring_login.springweb.service;

import hello.spring_login.springweb.application.request.LoginRequest;
import hello.spring_login.springweb.application.request.SignUpRequest;
import hello.spring_login.springweb.dto.LoginDTO;
import jakarta.servlet.http.HttpSession;

public interface LoginService {

    LoginDTO signUp(SignUpRequest signUpRequest , HttpSession session);

    LoginDTO login(LoginRequest loginRequest , HttpSession session);

    void logout(HttpSession httpSession);
}

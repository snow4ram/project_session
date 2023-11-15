package hello.spring_login.springweb.service;

import hello.spring_login.springweb.application.request.LoginRequest;
import jakarta.servlet.http.HttpSession;

public interface LoginService {

    void signUp(LoginRequest loginRequest , HttpSession session);

    void login(LoginRequest loginRequest , HttpSession session);

    void logout(HttpSession httpSession);
}

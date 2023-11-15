package hello.spring_login.springweb.dto;

import hello.spring_login.springweb.application.request.LoginRequest;
import hello.spring_login.springweb.entity.User;

import java.util.function.Function;

public record LoginDTO (
        String userid,
        String password,

        String uniqueID
){

    public static LoginDTO of(final User user) {
        if (user == null) {
            throw new RuntimeException("----------------------");
        }
        return new LoginDTO(user.getUserid(), user.getPassword() , user.getUserid());
    }



}

package hello.spring_login.springweb.application.response;


import hello.spring_login.springweb.dto.LoginDTO;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginResponse {

    private String userid;

    private String password;

    private String uniqueID;

    public LoginResponse(final LoginDTO loginDTO) {
        this.userid = loginDTO.userid();
        this.password = loginDTO.password();
        this.uniqueID = loginDTO.uniqueID();
    }

}

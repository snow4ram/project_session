package hello.spring_login.springweb.application.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank
    private String userid;

    @NotBlank
    private String password;

    public SignUpRequest(final String userid, final String password) {
        this.userid = userid;
        this.password = password;
    }

}

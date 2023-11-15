package hello.spring_login.springweb.application.request;


import lombok.Getter;
import lombok.Setter;

@Getter
public class LoginRequest {

    private String userid;

    private String password;

    public LoginRequest(final String userid,final String password) {
        this.userid = userid;
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest[" +
                "userid=" + userid  +
                ", password=" + password + '\'' +
                ']';
    }
}

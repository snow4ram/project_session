package hello.spring_login.springweb.exception;

public class LoginRuntimeException extends RuntimeException{

    public LoginRuntimeException(String exceptionMessage) {
        super(exceptionMessage);
    }
}

package hello.spring_login.springweb.entity;

public enum ExceptionInformation {

    LOGIN_ERROR("아이디 , 비밀번호가 일치하지 않습니다."),

    ERROR_MESSAGE("사용자 정보가 없습니다");

    private final String exceptionMessage;

    ExceptionInformation(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}

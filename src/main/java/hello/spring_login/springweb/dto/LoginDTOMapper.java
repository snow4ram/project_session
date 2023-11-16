package hello.spring_login.springweb.dto;

import hello.spring_login.springweb.entity.ExceptionInformation;
import hello.spring_login.springweb.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static hello.spring_login.springweb.entity.ExceptionInformation.*;

@Service
public class LoginDTOMapper implements Function<User , LoginDTO> {

    private static final String MESSAGE = "사용자 정보가 없습니다.";

    @Override
    public LoginDTO apply(User user) {
        if (user == null) {
            throw new RuntimeException(ERROR_MESSAGE.getExceptionMessage());
        }
        return new LoginDTO(user.getId(), user.getUserid(), user.getPassword(), user.getUniqueID());
    }
}

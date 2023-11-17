package hello.spring_login.springweb.repository;

import hello.spring_login.springweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User , Long> {

    Optional<User> findByUserid(String userid);

    Optional<User> findByUseridAndPassword(String userid , String password);
}

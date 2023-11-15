package hello.spring_login.springweb.repository;

import hello.spring_login.springweb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User , Long> {
}

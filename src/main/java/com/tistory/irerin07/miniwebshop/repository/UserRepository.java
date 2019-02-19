package com.tistory.irerin07.miniwebshop.repository;

import com.tistory.irerin07.miniwebshop.domain.User;
import com.tistory.irerin07.miniwebshop.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User getUserByEmail(@Param("email") String email);

//    @Query("select u from User u inner join fetch u.roles where email = :email")
//    public User getUserByEmail(@Param("email") String email);
}

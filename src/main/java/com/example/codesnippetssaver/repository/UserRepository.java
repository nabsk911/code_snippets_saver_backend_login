package com.example.codesnippetssaver.repository;



import com.example.codesnippetssaver.model.User; // Updated from Customer to User
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // Updated from CustomerRepository to UserRepository

    @Query("SELECT user FROM User user WHERE user.email = ?1") // Updated from Customer to User
    Optional<User> findUserByEmail(String email); // Updated from findCustomerByEmail to findUserByEmail

}

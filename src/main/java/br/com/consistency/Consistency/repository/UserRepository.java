package br.com.consistency.Consistency.repository;


import br.com.consistency.Consistency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstById(Long id);
}
package md.bookstore.repository;

import md.bookstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT c FROM Customer c JOIN c.user u WHERE u.username = :email")
    Optional<Customer> getCustomerByUserEmail(String email);
}
package md.bookstore.repository;

import md.bookstore.entity.Sale;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
//    List<Sale> findAllByCustomerUser(User user, Pageable pageable);
    @Query(
            value = "SELECT s FROM Sale s JOIN s.customer c JOIN c.user u WHERE u.username = :email",
            countQuery = "SELECT count(s) FROM Sale s JOIN s.customer c JOIN c.user u WHERE u.username = :email"
    )
    List<Sale> findAllByCustomerEmail(String email, Pageable pageable);

    @Query(value = "SELECT s FROM Sale s JOIN s.customer c JOIN c.user u WHERE u.username = :email ORDER BY s.dateTime DESC")
    List<Sale> findAllByCustomerEmail(String email);
}
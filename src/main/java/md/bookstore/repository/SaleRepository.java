package md.bookstore.repository;

import md.bookstore.entity.Sale;
import md.bookstore.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findAllByCustomerUser(User user, Pageable pageable);
}
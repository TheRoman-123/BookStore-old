package md.bookstore.dao;

import md.bookstore.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDAO extends JpaRepository<Sale, Long> {
}
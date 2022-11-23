package md.bookstore.dao;

import md.bookstore.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface WarehouseDAO extends JpaRepository<Warehouse, Long> {
    @Query(value = "SELECT * FROM warehouse OFFSET :offset LIMIT :limit", nativeQuery = true)
    List<Warehouse> findAllWithOffsetAndLimit(
            @Param("offset") Integer offset,
            @Param("limit") Integer warehouseLimit
    );

    /*String query = "SELECT w.warehouse_id, w.price, b.title, " +
            "a.first_name || ' ' || a.last_name AS authors" +
            "FROM warehouse w JOIN book b ON w.book_id = b.book_id" +
            "JOIN book_author ba ON b.book_id = ba.book_id" +
            "JOIN author a ON ba.author_id = a.author_id";
    @Query(value = query, nativeQuery = true)
    List<Warehouse> getPrintableBooks();*/
}
package md.bookstore.dao;

import md.bookstore.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query(value = "SELECT * FROM publisher OFFSET :offset LIMIT :limit", nativeQuery = true)
    List<Publisher> findPublisherEntityWithOffsetAndLimit (
            @Param("offset") Integer offset,
            @Param("limit") Integer publisherLimit
    );
}
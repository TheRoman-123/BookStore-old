package md.bookstore.repository;

import md.bookstore.entity.LiteraryWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiteraryWorkRepository extends JpaRepository<LiteraryWork, Long> {
    @Query(value = "SELECT * FROM literature OFFSET :offset LIMIT :limit", nativeQuery = true)
    List<LiteraryWork> findLiteraryWorkEntityWithOffsetAndLimit (
            @Param("offset") Integer offset,
            @Param("limit") Integer bookLimit
    );
}
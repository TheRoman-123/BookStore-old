package md.bookstore.repository;

import md.bookstore.entity.LiteraryWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface LiteraryWorkRepository extends JpaRepository<LiteraryWork, Long> {
    boolean existsByTitle(String title);
    Set<LiteraryWork> getReferencesByIdIn(Collection<Long> id);
}
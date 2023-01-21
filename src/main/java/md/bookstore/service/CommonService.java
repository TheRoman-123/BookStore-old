package md.bookstore.service;

import md.bookstore.exception.IllegalPageException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public final class CommonService<T> {
    public <R> List<R> getAll(
            Integer pageNumber,
            Integer pageSize,
            String sortCriteria,
            boolean desc,
            JpaRepository<T, Long> repository,
            Function<? super T, ? extends R> mapper
    ) {
        if (pageSize <= 0 || pageNumber < 0) {
            throw new IllegalPageException("Invalid pageNumber or pageSize", pageNumber, pageSize);
        }
        Sort sort = (desc) ? Sort.by(sortCriteria).descending() : Sort.by(sortCriteria).ascending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        return repository.findAll(pageable)
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}

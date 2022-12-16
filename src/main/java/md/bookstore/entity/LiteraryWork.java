package md.bookstore.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "literature")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LiteraryWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "literature_id", nullable = false)
    private Long id;

    @Size(max = 40)
    @Column(length = 40, nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private Author author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "literature_genre",
            joinColumns = @JoinColumn(name = "literature_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @ToString.Exclude
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "literature_book",
            joinColumns = @JoinColumn(name = "literature_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @ToString.Exclude
    private Set<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LiteraryWork that = (LiteraryWork) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

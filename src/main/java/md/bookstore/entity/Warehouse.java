package md.bookstore.entity;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "warehouse_id", nullable = false)
    private Long id;

    @Column(precision = 6, scale = 2, nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer amount;

    @Size(max = 100)
    @Column(name = "image_path", nullable = false, length = 100)
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    @ToString.Exclude
    private Publisher publisher;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
    @ToString.Exclude
    private Set<Cart> carts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Warehouse warehouse = (Warehouse) o;
        return id != null && Objects.equals(id, warehouse.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

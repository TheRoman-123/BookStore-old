package md.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sale_id", nullable = false)
    private Long id;

    @Column(name = "date_time", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime dateTime;

    @Column(precision = 7, scale = 2, nullable = false)
    private Double cost;

    @Column(name = "confirmed", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean confirmed;

    @OneToMany(mappedBy = "sale")
    @ToString.Exclude
    private Set<Cart> carts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @ToString.Exclude
    private Customer customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sale sale = (Sale) o;
        return id != null && Objects.equals(id, sale.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

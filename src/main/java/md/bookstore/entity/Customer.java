package md.bookstore.entity;

import lombok.*;
import org.hibernate.Hibernate;

import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", nullable = false)
    private Long id;

    @Size(max = 20)
    @Column(name = "first_name", length = 20, nullable = false)
    private String firstName;

    @Size(max = 20)
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @Column(name = "birth_date", columnDefinition = "DATE")
    private LocalDate birthDate;

    @Email
    @Size(max = 50)
    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Pattern(regexp = "0[67]\\d{7}", message = "Enter valid phone number!")
    @Size(max = 9)
    @Column(name = "phone_number", length = 9, unique = true, nullable = false)
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    @ToString.Exclude
    private List<Sale> sales;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
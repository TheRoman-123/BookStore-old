package md.bookstore.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id", nullable = false)
    private Byte id;

    @Enumerated(EnumType.STRING)
    private Role authority;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authoritySet")
    private Set<User> users;

    public Authority(Role role) {
        this.authority = role;
    }

    @Override
    public String getAuthority() {
        return authority.name();
    }
}

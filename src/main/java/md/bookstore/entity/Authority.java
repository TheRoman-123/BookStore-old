package md.bookstore.entity;

import jakarta.persistence.Embeddable;
import org.springframework.security.core.GrantedAuthority;


//@Embeddable
public enum Authority implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

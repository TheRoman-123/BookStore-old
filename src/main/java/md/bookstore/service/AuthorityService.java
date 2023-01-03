package md.bookstore.service;

import lombok.RequiredArgsConstructor;
import md.bookstore.entity.Authority;
import md.bookstore.entity.Role;
import md.bookstore.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    Authority getAuthorityByRole(Role role) {
        return authorityRepository.findAuthorityByAuthority(role)
                .orElseThrow(
                        () -> new IllegalArgumentException("Provided role doesn't exist!")
                );
    }
}

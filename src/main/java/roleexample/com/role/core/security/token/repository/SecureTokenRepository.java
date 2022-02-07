package roleexample.com.role.core.security.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roleexample.com.role.core.security.jpa.SecureToken;

@Repository
public interface SecureTokenRepository extends JpaRepository<SecureToken, Long> {

    SecureToken findByToken(final String token);
    Long removeByToken(String token);
}
